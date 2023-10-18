package POJOS;

import java.io.Serializable;
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
	private List<Doctor> doctor_list;

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
		this.doctor_id = doctor_id;
	}

	public Elderly(String name, int dni, Date dob, int doctor_id) {
		super();
		this.elderly_id = elderly_id;
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
		return "\n [elderly_id=" + elderly_id + ", name=" + name + ", date of birth=" + dob + "]";
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

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elderly other = (Elderly) obj;
		return dob == other.dob && elderly_id == other.elderly_id && Objects.equals(name, other.name);
	}

}
