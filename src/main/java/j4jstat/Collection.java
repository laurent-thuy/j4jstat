package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {
	private Object version;
	private Object clazz;
	private Object href;
	private Object label;
	private Object updated;
	private Map<String, List<Relation>> link;
	public Object getVersion() {
		return version;
	}
	
	@JsonGetter("class")
	public Object getClazz() {
		return clazz;
	}
	public Object getHref() {
		return href;
	}
	public Object getLabel() {
		return label;
	}
	public Object getUpdated() {
		return updated;
	}
	public Map<String, List<Relation>> getLink() {
		return link;
	}
	
	
}
