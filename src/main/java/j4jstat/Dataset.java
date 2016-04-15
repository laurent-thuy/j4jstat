package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "dimension", "id", "size", "role" })
public class Dataset {
	private Object version;
	private Object clazz;
	private Object value;
	private Object status;
	private Map<String, Dimension> dimension;
	private Object label;
	private Object updated;
	private Object source;
	private Map<String, Object> extension;
	private Object href;
	private Map<String, List<Relation>> link;
	private List<String> note;
	private Map<String, Object> error;
	private Object id;
	private Object size;
	private Map<String, List<Object>> role;
	
	
	public Object getVersion() {
		return version;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public void setSize(Object size) {
		this.size = size;
	}

	public void setRole(Map<String, List<Object>> role) {
		this.role = role;
	}

	public void setDimension(Map<String, Dimension> dimension) {
		this.dimension = dimension;
	}

	public Object getId() {
		return id;
	}

	public Object getSize() {
		return size;
	}

	public Map<String, List<Object>> getRole() {
		return role;
	}

	@JsonGetter("class")
	public Object getClazz() {
		return clazz;
	}

	public Map<String, Dimension> getDimension() {
		return dimension;
	}

	public Object getValue() {
		return value;
	}

	public Object getStatus() {
		return status;
	}

	public Object getLabel() {
		return label;
	}

	public Object getUpdated() {
		return updated;
	}

	public Object getSource() {
		return source;
	}

	public Map<String, Object> getExtension() {
		return extension;
	}

	public Object getHref() {
		return href;
	}

	public Map<String, List<Relation>> getLink() {
		return link;
	}

	public List<String> getNote() {
		return note;
	}

	public Map<String, Object> getError() {
		return error;
	}
}
