package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
	private Object index;
	private Object label;
	private Map<String, List<Object>> child;
	private Map<String, List<Double>> coordinates;
	private Map<String, Map<String, Object>> unit;
	private Map<String, List<String>> note;

	public Object getIndex() {
		return index;
	}

	public Object getLabel() {
		return label;
	}

	public Map<String, List<Object>> getChild() {
		return child;
	}

	public Map<String, List<Double>> getCoordinates() {
		return coordinates;
	}

	public Map<String, Map<String, Object>> getUnit() {
		return unit;
	}

	public Map<String, List<String>> getNote() {
		return note;
	}

}
