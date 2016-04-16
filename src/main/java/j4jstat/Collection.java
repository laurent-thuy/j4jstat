package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection implements JsonStat{
	private String version;
	private String clazz;
	private String href;
	private Object label;
	private Object updated;
	private Map<String, List<Relation>> link;
	
	public String getVersion() {
		return version;
	}
	
	@JsonGetter("class")
	public String getClazz() {
		return clazz;
	}
	public String getHref() {
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
