package j4jstat;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "dimension", "id", "size", "role", "cells", "ntuples" })
public class Dataset implements JsonStat {
	private String version;
	private String clazz;
	private Object value;
	private Object status;
	private Map<String, Dimension> dimension;
	private Object label;
	private Object updated;
	private Object source;
	private Map<String, Object> extension;
	private String href;
	private Map<String, List<Relation>> link;
	private List<String> note;
	private Map<String, Object> error;
	private List<String> id;
	private List<Integer> size;
	private Map<String, List<Object>> role;
	private Map<List<Integer>, Cell> cells;

	public void displayCells() {
		for (List<Integer> ntuple : getCells().keySet()) {
			System.out.println("Cell: " + ntuple + " - " + getCells().get(ntuple).getValue());
			System.out.println("Labels: " + getCells().get(ntuple).getLabels());
			System.out.println("Indexes: " + getCells().get(ntuple).getIndexes());
		}
	}
	
	public Map<List<Integer>, Cell> getCells() {
		return cells;
	}

	public void setCells(Map<List<Integer>, Cell> cells) {
		this.cells = cells;
	}

	public String getVersion() {
		return version;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public void setSize(List<Integer> size) {
		this.size = size;
	}

	public void setRole(Map<String, List<Object>> role) {
		this.role = role;
	}

	public void setDimension(Map<String, Dimension> dimension) {
		this.dimension = dimension;
	}

	public List<String> getId() {
		return id;
	}

	public List<Integer> getSize() {
		return size;
	}

	public Map<String, List<Object>> getRole() {
		return role;
	}

	@JsonGetter("class")
	public String getClazz() {
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

	public String getHref() {
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

	public void setVersion(String version) {
		this.version = version;
	}

	/*
	 * package private methods
	 */
	Object getValueByNtuple(List<Integer> ntuple) {
		Object valueToReturn = null;
		int index = getValueIndexByNtuple(ntuple);
		if (getValue() instanceof List) {
			valueToReturn = ((List<Object>) getValue()).get(index);
		} else if (getValue() instanceof Map) {
			valueToReturn = ((Map<String, Dimension>) getValue()).get(index);
		} else {
			// TODO exception
		}
		
		// TODO test with value as map
		return valueToReturn;
	}

	/**
	 * Ntuple to index.
	 *
	 * @param ntuple
	 *            the ntuple
	 * @return the int
	 */
	int getValueIndexByNtuple(List<Integer> ntuple) {
		int index = 0;

		int mult = 1;
		int nDims = getSize().size();
		for (int i = 0; i < nDims; i++) {
			mult *= (i > 0) ? getSize().get(nDims - i) : 1;
			index += mult * ntuple.get(nDims - i - 1);
		}
		return index;
	}
	/*
	 * end package private methods
	 */

}
