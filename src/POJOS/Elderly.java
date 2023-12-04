package POJOS;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Elderly implements Serializable {

	private static final long serialVersionUID = -8265618237491167237L;
	private int elderly_id;
	private String name;
	private int dni;
	private int doctor_id;
	private Date dob;
	private String symptoms;
	private List<Doctor> doctor_list;
	private List<Task> task_list;


	public Elderly() {
		super();
		setDoctor_list(new ArrayList<Doctor>());
	}

	public Elderly(int elderly_id, String name, int dni, Date dob, int doctor_id) {
		super();
		this.elderly_id = elderly_id;
		this.dni = dni;
		this.name = name;
		this.dob = dob;
		this.doctor_id = doctor_id;
	}

	public Elderly(int elderly_id, String name, int dni, Date dob) {
		super();
		this.elderly_id = elderly_id;
		this.dni = dni;
		this.name = name;
		this.dob = dob;
	}

	public Elderly(String name, int dni, Date dob, int doctor_id) {
		super();
		this.dni = dni;
		this.name = name;
		this.dob = dob;
		this.doctor_id = doctor_id;
	}

	public Elderly(String name, Date dob) {
		super();
		this.name = name;
		this.dob = dob;
	}

	//"Elderly [elderly_id=" + elderly_id + ", name=" + name + ", dni=" + dni + ", doctor_id=" + doctor_id
	//+ ", dob=" + dob + ", ]";
	
	
	
	public Elderly(String elderly_text) throws ParseException {
		this.elderly_id = Integer.parseInt(elderly_text.substring(elderly_text.indexOf("elderly_id=") +11, elderly_text.indexOf(", name")));
		this.name = elderly_text.substring(elderly_text.indexOf("name=") + 5, elderly_text.indexOf(", dni"));
		this.dni = Integer.parseInt(elderly_text.substring(elderly_text.indexOf("dni=") +4, elderly_text.indexOf(", doctor_id")));
		this.doctor_id = Integer.parseInt(elderly_text.substring(elderly_text.indexOf("doctor_id=") +10, elderly_text.indexOf(", dob")));

		String date_text = "" + elderly_text.substring(elderly_text.indexOf("dob=") + 4, elderly_text.indexOf("]"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.dob = dateFormat.parse(date_text);

	}


	public Elderly(int elderly_id, String name, int dni, int doctor_id, Date dob, String symptoms,
			List<Doctor> doctor_list) {
		super();
		this.elderly_id = elderly_id;
		this.name = name;
		this.dni = dni;
		this.doctor_id = doctor_id;
		this.dob = dob;
		this.symptoms = symptoms;
		this.doctor_list = doctor_list;
	}

	public int getElderly_id() {
		return elderly_id;
	}

	public void setElderly_id(int elderly_id) {
		this.elderly_id = elderly_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Doctor> getDoctor_list() {
		return doctor_list;
	}

	public void setDoctor_list(List<Doctor> doctor_list) {
		this.doctor_list = doctor_list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "Elderly [elderly_id=" + elderly_id + ", name=" + name + ", dni=" + dni + ", doctor_id=" + doctor_id
				+ ", dob=" + dateFormat.format(dob) + "]";
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(elderly_id);
	}
	
	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public List<Task> getTask_list() {
		return task_list;
	}

	public void setTask_list(List<Task> task_list) {
		this.task_list = task_list;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Elderly other = (Elderly) obj;
		return dob == other.dob && elderly_id == other.elderly_id && Objects.equals(name, other.name);
	}

}
