package j4jstat;

import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStatFactory {
	static ObjectMapper mapper = new ObjectMapper();

	public static JsonStat getInstance(Object jsonUrlOrString) throws Exception {
		JsonStat jsonStat;
		JsonNode rootNode = null;

		if (jsonUrlOrString instanceof String) {
			rootNode = mapper.readValue((String) jsonUrlOrString, JsonNode.class);
		} else if (jsonUrlOrString instanceof URL) {
			rootNode = mapper.readValue((URL) jsonUrlOrString, JsonNode.class);
		} else {
			// TODO exception bad args
		}

		if (null != rootNode.get("version")) { // version >= 2
			jsonStat = getInstanceV2(rootNode);
		} else { // version 1.x
			jsonStat = getInstanceV1(rootNode);
		}

		return jsonStat;
	}

	private static JsonStat getInstanceV1(JsonNode rootNode) throws Exception {
		if (null != rootNode.get("dataset")) {
			return getDatasetInstanceV1(rootNode);
		}
		return null;
	}

	private static JsonStat getDatasetInstanceV1(JsonNode rootNode) throws Exception {
		JsonNode datasetNode = rootNode.get("dataset");
		Dataset dataset = mapper.treeToValue(datasetNode, Dataset.class);

		dataset.setClazz("dataset");
		dataset.setVersion("1.x");

		DatasetConfigurator.config(dataset, datasetNode);
		return dataset;
	}
	
	private static JsonStat getDatasetInstanceV2(JsonNode rootNode) throws Exception {
		Dataset dataset = mapper.treeToValue(rootNode, Dataset.class);
		DatasetConfigurator.config(dataset, rootNode);
		return dataset;
	}


	private static JsonStat getInstanceV2(JsonNode rootNode) throws Exception {
		switch (rootNode.get("class").asText()) {
		case "dataset":
			return getDatasetInstanceV2(rootNode);
		case "collection":
			return getCollectionInstanceV2(rootNode);
		case "dimension":
			return getdimensionInstanceV2(rootNode);
		default:
			// TODO exception unknown class
			return null;
		}
	}

	private static JsonStat getdimensionInstanceV2(JsonNode rootNode) throws Exception {
		return mapper.treeToValue(rootNode, Dimension.class);
	}

	
	private static JsonStat getCollectionInstanceV2(JsonNode rootNode) throws JsonProcessingException {
		return mapper.treeToValue(rootNode, Collection.class);
	}

}
