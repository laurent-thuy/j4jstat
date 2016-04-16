package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimension implements JsonStat {
	private String clazz;
	private Object label;
	private Category category;
	private Map<String, List<Relation>> link;
	private Map<String, Object> extension;
	private String href;
	private List<String> note;

	@JsonGetter("class")
	public String getClazz() {
		return clazz;
	}

	public Object getLabel() {
		return label;
	}

	public Category getCategory() {
		return category;
	}

	public Map<String, List<Relation>> getLink() {
		return link;
	}

	public Map<String, Object> getExtension() {
		return extension;
	}

	public String getHref() {
		return href;
	}

	public List<String> getNote() {
		return note;
	}

}
