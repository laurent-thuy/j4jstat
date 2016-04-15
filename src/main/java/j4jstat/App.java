package j4jstat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {

	static ObjectMapper mapper = new ObjectMapper();
	static String standAloneDim = "{\"version\":\"2.0\",\"class\":\"dimension\",\"label\":\"sex\",\"category\":{\"index\":[\"T\",\"M\",\"F\"],\"label\":{\"T\":\"total\",\"M\":\"male\",\"F\":\"female\"}}}";
	static URL collectionV2Url;
	static URL datasetV2Url;
	static URL datasetV1Url;

	static {
		try {
			collectionV2Url = new URL("http://json-stat.org/samples/collection.json");
			datasetV2Url = new URL("http://json-stat.org/samples/oecd.json");
			datasetV1Url = new URL("https://data.ssb.no/api/v0/dataset/1052.json?lang=en");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		JsonNode rootNode = mapper.readValue(datasetV2Url, JsonNode.class);
		// JsonNode rootNode = mapper.readValue(standAloneDim, JsonNode.class);
		// JsonNode rootNode = mapper.readValue(collectionV2Url,
		// JsonNode.class);
		// JsonNode rootNode = mapper.readValue(datasetV1Url, JsonNode.class);

		System.out.println("version: " + rootNode.get("version"));
		System.out.println("class: " + rootNode.get("class"));

		if (null != rootNode.get("version")) {
			processV2(rootNode);
		} else {
			processV1(rootNode);
		}

	}

	private static void processV1(JsonNode rootNode) throws JsonProcessingException {
		if (null != rootNode.get("dataset")) {
			processDatasetV1(rootNode);
		}
	}

	private static void processDatasetV1(JsonNode rootNode) throws JsonProcessingException {
		JsonNode datasetNode = rootNode.get("dataset");
		JsonNode dimNode = datasetNode.get("dimension");
		Dataset dataset = mapper.treeToValue(datasetNode, Dataset.class);

		fixDimensionToV2Format(dataset, dimNode);
		displayDataset(dataset);
	}

	private static void fixDimensionToV2Format(Dataset dataset, JsonNode dimensionNode) throws JsonProcessingException {
		Map<String, Object> dimensionNodeAsMap = mapper.treeToValue(dimensionNode, Map.class);
		Map<String, Dimension> dimensionMapV2Format = new LinkedHashMap<>();
		for (String key : dimensionNodeAsMap.keySet()) {
			if (key.equals("id")) {
				dataset.setId(dimensionNodeAsMap.get(key));
				continue;
			}
			if (key.equals("size")) {
				dataset.setSize(dimensionNodeAsMap.get(key));
				continue;
			}
			if (key.equals("role")) {
				dataset.setRole((Map<String, List<Object>>) dimensionNodeAsMap.get(key));
				continue;
			}

			Dimension dimension = mapper.treeToValue(dimensionNode.get(key), Dimension.class);
			dimensionMapV2Format.put(key, dimension);

		}

		dataset.setDimension(dimensionMapV2Format);

	}

	private static void displayDataset(Dataset dataset) {
		System.out.println("***ROOT LEVEL");
		System.out.println("     class: " + dataset.getClazz());
		System.out.println("     value: " + dataset.getValue());
		System.out.println("     status: " + dataset.getStatus());
		System.out.println("     dimensions list: " + dataset.getDimension());
		System.out.println("     label: " + dataset.getLabel());
		System.out.println("     updated: " + dataset.getUpdated());
		System.out.println("     source: " + dataset.getSource());
		System.out.println("     extension: " + dataset.getExtension());
		System.out.println("     href: " + dataset.getHref());

		System.out.println("     id: " + dataset.getId());
		System.out.println("     size: " + dataset.getSize());

		System.out.println();
		System.out.println("***ROLE");
		if (null != dataset.getRole()) {
			for (String roleID : dataset.getRole().keySet()) {
				System.out.println("     role: " + roleID);
				System.out.println("     dimensions with this role: " + dataset.getRole().get(roleID));
			}
		}
		System.out.println("***END ROLE");
		System.out.println();

		System.out.println();
		System.out.println("***EXTENSION");
		if (null != dataset.getExtension()) {
			for (String extentionID : dataset.getExtension().keySet()) {
				System.out.println("     extensionID: " + extentionID);
				System.out.println("     extension object: " + dataset.getExtension().get(extentionID));
			}
		}
		System.out.println("***END EXTENSION");
		System.out.println();

		System.out.println("***LINKS");
		if (null != dataset.getLink()) {
			for (String relationID : dataset.getLink().keySet()) {
				System.out.println("     relationID: " + relationID);
				for (Relation relation : dataset.getLink().get(relationID)) {
					System.out.println("     relation clazz: " + relation.getClazz());
					System.out.println("     relation href: " + relation.getHref());
					System.out.println("     relation type: " + relation.getType());
				}
			}
		}
		System.out.println("***END LINKS");
		System.out.println();

		System.out.println();
		System.out.println("***NOTE");
		if (null != dataset.getNote()) {
			for (String note : dataset.getNote()) {
				System.out.println("     note: " + note);
			}
		}
		System.out.println("***END NOTE");
		System.out.println();

		System.out.println();
		System.out.println("***ERROR");
		if (null != dataset.getError()) {
			for (String errorID : dataset.getError().keySet()) {
				System.out.println("     errorID: " + errorID);
				System.out.println("     error object: " + dataset.getError().get(errorID));
			}
		}
		System.out.println("***END ERROR");
		System.out.println();

		System.out.println();
		System.out.println("*********************DIMENSIONS LEVEL");

		for (String dimensionID : dataset.getDimension().keySet()) {
			System.out.println(" *******************id: " + dimensionID);

			Dimension dimension = dataset.getDimension().get(dimensionID);
			displayDimension(dimension);
		}

		System.out.println("*********************END DIMENSIONS LEVEL");
		System.out.println();

	}

	private static void processV2(JsonNode rootNode) throws JsonProcessingException {
		switch (rootNode.get("class").asText()) {
		case "dataset":
			processDatasetV2(rootNode);
			break;
		case "collection":
			processCollV2(rootNode);
			break;
		case "dimension":
			processDimV2(rootNode);
			break;
		default:
			// TODO exception unknown class
			break;
		}
	}

	private static void processDimV2(JsonNode rootNode) throws JsonProcessingException {
		Dimension dimension = mapper.treeToValue(rootNode, Dimension.class);
		displayDimension(dimension);
	}

	private static void processCollV2(JsonNode rootNode) throws JsonProcessingException {
		Collection coll = mapper.treeToValue(rootNode, Collection.class);
		displayCollV2(coll);

	}

	private static void displayCollV2(Collection coll) {
		System.out.println("***ROOT LEVEL");
		System.out.println("     version: " + coll.getVersion());
		System.out.println("     class: " + coll.getClazz());
		System.out.println("     label: " + coll.getLabel());
		System.out.println("     updated: " + coll.getUpdated());
		System.out.println("     href: " + coll.getHref());
	}

	private static void processDatasetV2(JsonNode rootNode) throws JsonProcessingException {
		Dataset dataset = mapper.treeToValue(rootNode, Dataset.class);
		dataset.setId(mapper.treeToValue(rootNode.get("id"), Object.class));
		dataset.setSize(mapper.treeToValue(rootNode.get("size"), Object.class));
		if (null != rootNode.get("role")) {
			dataset.setRole(mapper.treeToValue(rootNode.get("role"), Map.class));
		}

		JsonNode dimensionNode = rootNode.get("dimension");
		Map<String, Object> dimensionNodeAsMap = mapper.treeToValue(dimensionNode, Map.class);
		Map<String, Dimension> dimensionMapV2Format = new LinkedHashMap<>();

		for (String key : dimensionNodeAsMap.keySet()) {
			Dimension dimension = mapper.treeToValue(dimensionNode.get(key), Dimension.class);
			dimensionMapV2Format.put(key, dimension);
		}

		dataset.setDimension(dimensionMapV2Format);
		displayDataset(dataset);
	}

	private static void displayDimension(Dimension dimension) {

		System.out.println("     clazz: " + dimension.getClazz());
		System.out.println("     label: " + dimension.getLabel());
		System.out.println("     href: " + dimension.getHref());

		System.out.println();
		System.out.println("***NOTE");
		if (null != dimension.getNote()) {
			for (String note : dimension.getNote()) {
				System.out.println("     note: " + note);
			}
		}
		System.out.println("***END NOTE");
		System.out.println();

		System.out.println();
		System.out.println("***EXTENSION");
		if (null != dimension.getExtension()) {
			for (String extentionID : dimension.getExtension().keySet()) {
				System.out.println("     extensionID: " + extentionID);
				System.out.println("     extension object: " + dimension.getExtension().get(extentionID));
			}
		}
		System.out.println("***END EXTENSION");
		System.out.println();

		System.out.println("***LINKS");
		if (null != dimension.getLink()) {
			for (String relationID : dimension.getLink().keySet()) {
				System.out.println("     relationID: " + relationID);
				for (Relation relation : dimension.getLink().get(relationID)) {
					System.out.println("     relation clazz: " + relation.getClazz());

					System.out.println();
					System.out.println("***EXTENSION");
					if (null != relation.getExtension()) {
						for (String extentionID : relation.getExtension().keySet()) {
							System.out.println("     extensionID: " + extentionID);
							System.out.println("     extension object: " + relation.getExtension().get(extentionID));
						}
					}
					System.out.println("***END EXTENSION");
					System.out.println();

					System.out.println("     relation href: " + relation.getHref());
					System.out.println("     relation type: " + relation.getType());
				}
			}
		}
		System.out.println("***END LINKS");
		System.out.println();

		System.out.println("***CATEGORY");
		System.out.println("     category label: " + dimension.getCategory().getLabel());
		System.out.println("     category index: " + dimension.getCategory().getIndex());

		System.out.println();
		System.out.println("***NOTE");
		if (null != dimension.getCategory().getNote()) {
			for (String noteId : dimension.getCategory().getNote().keySet()) {
				System.out.println("     noteID: " + noteId);
				for (String note : dimension.getCategory().getNote().get(noteId)) {
					System.out.println(note);
				}
			}
		}
		System.out.println("***END NOTE");
		System.out.println();

		System.out.println("***UNIT");
		if (null != dimension.getCategory().getUnit()) {
			for (String unitID : dimension.getCategory().getUnit().keySet()) {
				System.out.println("     unitID: " + unitID);
				for (String unit : dimension.getCategory().getUnit().get(unitID).keySet()) {
					System.out.print("     " + unit);
					System.out.println(": " + dimension.getCategory().getUnit().get(unitID).get(unit));
				}
			}
		}
		System.out.println("***END UNIT");
		System.out.println();

		System.out.println("***COORDINATES");
		if (null != dimension.getCategory().getCoordinates()) {
			for (String coorID : dimension.getCategory().getCoordinates().keySet()) {
				System.out.println("     coorID: " + coorID);
				System.out.println("     longitude: " + dimension.getCategory().getCoordinates().get(coorID).get(0));
				System.out.println("     latitude: " + dimension.getCategory().getCoordinates().get(coorID).get(1));
			}
		}
		System.out.println("***END COORDINATES");
		System.out.println();

		System.out.println("***CHILD");
		if (null != dimension.getCategory().getChild()) {
			for (String childID : dimension.getCategory().getChild().keySet()) {
				System.out.println("     childID: " + childID);
				System.out.println("     child list: " + dimension.getCategory().getChild().get(childID));
			}
		}
		System.out.println("***END CHILD");
		System.out.println();

		System.out.println("***END CATEGORY");
		System.out.println();

	}

}
