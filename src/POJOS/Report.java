package POJOS;

import java.util.Objects;

public class Report {

	private int report_id;
	private String file_name;
	private int task_id;
	private int elderly_id;


	public Report() {
		super();
	}
	/**
	Constructs a Report object with the specified attributes.
	@param report_id the unique identifier for the report
	@param file_name the name of the file
	@param task_id the unique identifier for the task
	@param elderly_id the unique identifier for the elderly person
	 */
	public Report(int report_id, String file_name, int task_id, int elderly_id) {
		super();
		this.report_id = report_id;
		this.file_name = file_name;
		this.task_id = task_id;
		this.elderly_id = elderly_id;
	}

	/**
	Constructs a Report object with the specified attributes.
	@param file_name the name of the file
	@param task_id the unique identifier for the task
	@param elderly_id the unique identifier for the elderly person
	 */
	public Report(String file_name, int task_id, int elderly_id) {
		super();
		this.file_name = file_name;
		this.task_id = task_id;
		this.elderly_id = elderly_id;
	}
	/**
	Retrieves the unique identifier for the report.
	@return the unique identifier for the report
	 */
	public int getReport_id() {
		return report_id;
	}
	/**
	Sets the unique identifier for the report.
	@param report_id the unique identifier for the report
	 */
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	/**
	Retrieves the name of the file.
	@return the name of the file
	 */
	public String getFile_name() {
		return file_name;
	}
	/**
	Sets the name of the file.
	@param file_name the name of the file
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	/**
	Retrieves the unique identifier for the task.
	@return the unique identifier for the task
	 */
	public int getTask_id() {
		return task_id;
	}
	/**
	Sets the unique identifier for the task.
	@param task_id the unique identifier for the task
	 */
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	/**
	Retrieves the unique identifier for the elderly person.
	@return the unique identifier for the elderly person
	 */
	public int getElderly_id() {
		return elderly_id;
	}
	/**
	Sets the unique identifier for the elderly person.
	@param elderly_id the unique identifier for the elderly person
	 */
	public void setElderly_id(int elderly_id) {
		this.elderly_id = elderly_id;
	}


	/**
	 * Returns a hash code value for the Report object based on the specified attributes.
	 *
	 * @return a hash code value for the Report object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(elderly_id, file_name, report_id, task_id);
	}
	/**
	 * Indicates whether some other object is "equal to" this one based on the specified attributes.
	 *
	 * @param obj the reference object with which to compare
	 * @return true if this object is the same as the obj argument; false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Report other = (Report) obj;
		return elderly_id == other.elderly_id && Objects.equals(file_name, other.file_name)
				&& report_id == other.report_id && task_id == other.task_id;
	}

	/**
	 * Returns a string representation of the Report object.
	 *
	 * @return a string representation of the Report object
	 */
	@Override
	public String toString() {
		return "Report [report_id=" + report_id + ", file_name=" + file_name + ", task_id=" + task_id + ", elderly_id="
				+ elderly_id + "]";
	}
	/**
	 * Constructs a Report object from the provided string representation.
	 *
	 * @param report_string the string representation of the Report object
	 */
	public Report (String report_string) {
		this.report_id = Integer.parseInt(report_string.substring(report_string.indexOf("report_id=") +10, report_string.indexOf(", file_name")));
		this.file_name = report_string.substring(report_string.indexOf("file_name=") + 10, report_string.indexOf(", task_id"));
		this.task_id = Integer.parseInt(report_string.substring(report_string.indexOf("task_id=") +8, report_string.indexOf(", elderly_id")));
		this.elderly_id = Integer.parseInt(report_string.substring(report_string.indexOf("elderly_id=") +11, report_string.indexOf("]")));
	}

}