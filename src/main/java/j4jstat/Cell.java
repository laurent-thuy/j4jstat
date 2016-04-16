package j4jstat;

import java.util.List;

public class Cell {

	private Object value;
	private List<String> labels;
	private List<String> indexes;

	/**
	 * Instantiates a new cell.
	 *
	 * @param labels
	 *            the labels for the value in this cell
	 * @param value
	 *            the value in this cell
	 */
	Cell(Object value, List<String> labels, List<String> indexes) {
		this.value = value;
		this.labels = labels;
		this.indexes = indexes;
	}

	/**
	 * Gets the value in this cell
	 *
	 * @return the value in this cell
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Gets the labels for this cell
	 *
	 * @return the labels for this cell
	 */
	public List<String> getLabels() {
		return labels;
	}

	/**
	 * Gets the indexes for this cell
	 *
	 * @return the indexes for this cell
	 */
	public List<String> getIndexes() {
		return indexes;
	}

}
