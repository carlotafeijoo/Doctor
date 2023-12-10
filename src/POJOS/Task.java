package POJOS;

import java.util.Objects;

public class Task {

	private int task_id;
	private String description;
	private int doctor_id;
	private int duration;
	private int elderly_id;

	public Task() {
		super();
	}
	/**
	 * Constructs a Task object with the specified description, doctor ID, duration, and elderly ID.
	 *
	 * @param description the description of the task
	 * @param doctor_id the unique identifier for the doctor
	 * @param duration the duration of the task
	 * @param elderly_id the unique identifier for the elderly person
	 */
	public Task(String description, int doctor_id, int duration, int elderly_id) {
		super();
		this.task_id = task_id;
		this.description = description;
		this.doctor_id = doctor_id;
		this.duration = duration;
		this.elderly_id = elderly_id;
	}
	/**
	 * Constructs a Task object with the specified task ID, description, doctor ID, duration, and elderly ID.
	 *
	 * @param task_id the unique identifier for the task
	 * @param description the description of the task
	 * @param doctor_id the unique identifier for the doctor
	 * @param duration the duration of the task
	 * @param elderly_id the unique identifier for the elderly person
	 */
	public Task(int task_id, String description, int doctor_id, int duration, int elderly_id) {
		super();
		this.task_id = task_id;
		this.description = description;
		this.doctor_id = doctor_id;
		this.duration = duration;
		this.elderly_id = elderly_id;
	}


	/**
	 * Constructs a Task object with the specified task ID, description, and doctor ID.
	 *
	 * @param task_id the unique identifier for the task
	 * @param description the description of the task
	 * @param doctor_id the unique identifier for the doctor
	 */
	public Task(int task_id, String description, int doctor_id) {
		super();
		this.task_id = task_id;
		this.description = description;
		this.doctor_id = doctor_id;
	}
	/**
	 * Constructs a Task object with the specified description and duration.
	 *
	 * @param description the description of the task
	 * @param duration the duration of the task
	 */
	public Task(String description, int duration) {
		super();
		this.description = description;
		this.doctor_id = duration;
	}
	/**
	 * Constructs a Task object with the specified task ID.
	 *
	 * @param task_id the unique identifier for the task
	 */
	public Task(int task_id) {
		super();
		this.task_id = task_id;

	}
	/**
	 * Constructs a Task object from the provided string representation.
	 *
	 * @param task_string the string representation of the Task object
	 */
	public Task (String task_string) {
		this.task_id = Integer.parseInt(task_string.substring(task_string.indexOf("task_id=") +8, task_string.indexOf(", description")));
		this.description = task_string.substring(task_string.indexOf("description=") + 12, task_string.indexOf(", doctor_id"));
		this.doctor_id = Integer.parseInt(task_string.substring(task_string.indexOf("doctor_id=") +10, task_string.indexOf(", duration")));
		this.duration = Integer.parseInt(task_string.substring(task_string.indexOf("duration=") +9, task_string.indexOf  (", elderly_id")));
		this.elderly_id = Integer.parseInt(task_string.substring(task_string.indexOf("elderly_id=") +11, task_string.indexOf("]")));
	}

	/**
	 * Retrieves the unique identifier for the task.
	 *
	 * @return the unique identifier for the task
	 */
	public int getTask_id() {
		return task_id;
	}
	/**
	 * Sets the unique identifier for the task.
	 *
	 * @param task_id the unique identifier for the task
	 */
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	/**
	 * Retrieves the unique identifier for the doctor associated with the task.
	 *
	 * @return the unique identifier for the doctor associated with the task
	 */
	public int getDoctor_id() {
		return doctor_id;
	}
	/**
	 * Sets the unique identifier for the doctor associated with the task.
	 *
	 * @param doctor_id the unique identifier for the doctor associated with the task
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	/**
	 * Retrieves the description of the task.
	 *
	 * @return the description of the task
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the task.
	 *
	 * @param description the description of the task
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Retrieves the duration of the task.
	 *
	 * @return the duration of the task
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * Sets the duration of the task.
	 *
	 * @param duration the duration of the task
	 */
	public void setDuration(int elderly_id) {
		this.elderly_id = elderly_id;
	}

	/**
	 * Retrieves the unique identifier for the elderly person associated with the task.
	 *
	 * @return the unique identifier for the elderly person associated with the task
	 */
	public int getElderly_id() {
		return elderly_id;
	}
	/**
	 * Sets the unique identifier for the elderly person associated with the task.
	 *
	 * @param elderly_id the unique identifier for the elderly person associated with the task
	 */
	public void setElderly_id(int elderly_id) {
		this.elderly_id = elderly_id;
	}

	/**
	 * Returns a hash code value for the Task object based on the task ID.
	 *
	 * @return a hash code value for the Task object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(task_id);
	}
	/**
	 * Indicates whether some other object is "equal to" this one based on the description.
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
		Task other = (Task) obj;
		return Objects.equals(description, other.description);
	}
	/**
	 * Returns a string representation of the Task object.
	 *
	 * @return a string representation of the Task object
	 */
	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", description=" + description + ", doctor_id=" + doctor_id + ", duration="
				+ duration + ", elderly_id=" + elderly_id + "]";
	}
	/**
	 * Returns a string representation of the Task object specifically for the elderly person.
	 *
	 * @return a string representation of the Task object for the elderly person
	 */
	public String toStringtoElderly() {
		return "Task: " + this.description + " Duration : " + this.duration;
	}

}
