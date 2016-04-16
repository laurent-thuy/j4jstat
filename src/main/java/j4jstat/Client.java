package j4jstat;

import java.net.MalformedURLException;
import java.net.URL;

public class Client {

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

	public static void main(String[] args) throws Exception {
		
		JsonStat jsonStat = JsonStatFactory.getInstance(datasetV1Url);

		switch (jsonStat.getClazz()) {
		case "collection":
			Collection collection = (Collection) jsonStat;			
			Display.displayCollection(collection);
			break;
		case "dataset":
			Dataset dataset = (Dataset) jsonStat;
			//Display.displayDataset(dataset);
			break;
		case "dimension":
			Dimension dimension = (Dimension) jsonStat;
			Display.displayDimension(dimension);
			break;
		default:
			break;
		}

	}

}
