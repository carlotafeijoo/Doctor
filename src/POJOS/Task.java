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

	public Task(String description, int doctor_id, int duration, int elderly_id) {
		super();
		this.task_id = task_id;
		this.description = description;
		this.doctor_id = doctor_id;
		this.duration = duration;
		this.elderly_id = elderly_id;
	}

	public Task(int task_id, String description, int doctor_id, int duration, int elderly_id) {
		super();
		this.task_id = task_id;
		this.description = description;
		this.doctor_id = doctor_id;
		this.duration = duration;
		this.elderly_id = elderly_id;
	}

	// NUEVO CONSTRUCTOR PENTIENTE CON duration -> PARA EVITAR ERRORES Y ENTENDER
	// FUNCIONAMENTO
	public Task(int task_id, String description, int doctor_id) {
		super();
		this.task_id = task_id;
		this.description = description;
		this.doctor_id = doctor_id;
	}

	public Task(String description, int duration) {
		super();
		this.description = description;
		this.doctor_id = duration;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int elderly_id) {
		this.elderly_id = elderly_id;
	}

	public int getElderly_id() {
		return elderly_id;
	}

	public void setElderly_id(int elderly_id) {
		this.elderly_id = elderly_id;
	}

	// Has an equals (uses only description)
	@Override
	public int hashCode() {
		return Objects.hash(task_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", description=" + description + ", doctor_id=" + doctor_id + ", duration="
				+ duration + ", elderly_id=" + elderly_id + "]";
	}
	
	public String toStringtoElderly() {
		return "Task: "+this.description + " Duration : "+ this.duration;
	}

}
