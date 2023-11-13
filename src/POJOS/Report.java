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

	public Report(int report_id, String file_name, int task_id, int elderly_id) {
		super();
		this.report_id = report_id;
		this.file_name = file_name;
		this.task_id = task_id;
		this.elderly_id = elderly_id;
	}


	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public int getElderly_id() {
		return elderly_id;
	}

	public void setElderly_id(int elderly_id) {
		this.elderly_id = elderly_id;
	}



	@Override
	public int hashCode() {
		return Objects.hash(elderly_id, file_name, report_id, task_id);
	}

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



	@Override
	public String toString() {
		return "Report [report_id=" + report_id + ", file_name=" + file_name + ", task_id=" + task_id + ", elderly_id="
				+ elderly_id + "]";
	}

	public Report (String report_string) {
		this.report_id = Integer.parseInt(report_string.substring(report_string.indexOf("report_id=") +8, report_string.indexOf(", file_name")));
		this.file_name = report_string.substring(report_string.indexOf("file_name=") + 12, report_string.indexOf(", task_id"));
		this.task_id = Integer.parseInt(report_string.substring(report_string.indexOf("task_id=") +10, report_string.indexOf(", elderly_id")));
		this.elderly_id = Integer.parseInt(report_string.substring(report_string.indexOf("elderly_id=") +11, report_string.indexOf("]")));
	}

}