package j4jstat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;

public class DatasetConfigurator {
	static ObjectMapper mapper = new ObjectMapper();

	static void config(Dataset dataset, JsonNode datasetNode) throws Exception {
		setDimensionToV2Format(dataset, datasetNode);
		Set<List<Integer>> ntuples = createNtuples(dataset);
		createCells(dataset, ntuples);
	}

	@SuppressWarnings("unchecked")
	private static void setDimensionToV2Format(Dataset dataset, JsonNode datasetNode) throws JsonProcessingException {
		JsonNode dimensionNode = datasetNode.get("dimension");

		Map<String, Object> dimensionNodeAsMap = mapper.treeToValue(dimensionNode, Map.class);
		Map<String, Dimension> dimensionMapV2Format = new LinkedHashMap<>();

		if (dataset.getVersion().equals("1.x")) {
			dataset.setId(mapper.treeToValue(dimensionNode.get("id"), List.class));
			dataset.setSize(mapper.treeToValue(dimensionNode.get("size"), List.class));
			dataset.setRole(mapper.treeToValue(dimensionNode.get("role"), Map.class));
		} else { // version >=2
			dataset.setId(mapper.treeToValue(datasetNode.get("id"), List.class));
			dataset.setSize(mapper.treeToValue(datasetNode.get("size"), List.class));
			dataset.setRole(mapper.treeToValue(datasetNode.get("role"), Map.class));
		}

		for (String key : dimensionNodeAsMap.keySet()) {
			if (!key.equals("id") && !key.equals("size") && !key.equals("role")) {
				Dimension dimension = mapper.treeToValue(dimensionNode.get(key), Dimension.class);
				dimensionMapV2Format.put(key, dimension);
			}
		}
		dataset.setDimension(dimensionMapV2Format);
	}

	// Build n-tuples representing all possible combinations of the categories
	// within each dimension.
	// For each dimension create a set of all categories.
	// Create a list of all theses sets .
	/**
	 * Creates the ntuples.
	 */
	// Get the cartesian product.
	private static Set<List<Integer>> createNtuples(Dataset dataset) {
		List<Set<Integer>> ntupleList = new ArrayList<>();

		for (Integer dimensionSize : dataset.getSize()) {
			Set<Integer> set = new TreeSet<>();
			for (Integer i = 0; i < dimensionSize; i++) {
				set.add(i);
			}
			ntupleList.add(set);
		}

		return Sets.cartesianProduct(ntupleList);
	}

	// for each ntuple create cell object containing labels, indexes and value
	/**
	 * Creates the cells.
	 */
	// put all cells object in a map with ntuple as key
	private static void createCells(Dataset dataset, Set<List<Integer>> ntuples) {
		Map<List<Integer>, Cell> cells = new LinkedHashMap<>();
		for (List<Integer> ntuple : ntuples) {
			List<String> labels = new ArrayList<>();
			List<String> indexes = new ArrayList<>();
			for (int dimensionIndex = 0; dimensionIndex < ntuple.size(); dimensionIndex++) {
				String dimensionId = dataset.getId().get(dimensionIndex);
				Dimension dimension = dataset.getDimension().get(dimensionId);
				Category category = dimension.getCategory();
				int categoryIndex = ntuple.get(dimensionIndex);

				if (null != category.getLabel()) {
					String label = (String) category.getLabel().values().toArray()[categoryIndex]; 
					labels.add(label);
				}
				if (null != category.getIndex()) {
					if (category.getIndex() instanceof List) {
						String index = (String) ((List<?>) category.getIndex()).get(categoryIndex);
						indexes.add(index);
					} else if (category.getIndex() instanceof Map) {
						String index = (String) ((Map<?, ?>) category.getIndex()).keySet().toArray()[categoryIndex];
						indexes.add(index);
					} else {
						// TODO exception
					}
				}
			}

			Object value = dataset.getValueByNtuple(ntuple);
			cells.put(ntuple, new Cell(value, labels, indexes));
		}
		dataset.setCells(cells);
		dataset.displayCells();

	}

}
