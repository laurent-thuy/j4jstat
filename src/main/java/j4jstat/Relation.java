package j4jstat;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Relation {
	private Object type;
	private String href;
	private Object clazz;
	private Map<String, Object> extension;

	public Object getType() {
		return type;
	}

	public String getHref() {
		return href;
	}

	@JsonGetter("class")
	public Object getClazz() {
		return clazz;
	}

	public Map<String, Object> getExtension() {
		return extension;
	}

}
