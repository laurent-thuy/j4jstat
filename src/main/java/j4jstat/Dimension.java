package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimension {
	private Object clazz;
	private Object label;
	private Category category;
	private Map<String, List<Relation>> link;
	private Map<String, Object> extension;
	private Object href;
	private List<String> note;
	
	@JsonGetter("class")
	public Object getClazz() {
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

	public Object getHref() {
		return href;
	}

	public List<String> getNote() {
		return note;
	}

}
