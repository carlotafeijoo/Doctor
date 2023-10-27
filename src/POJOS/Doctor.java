package POJOS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Doctor {

	private int doctor_id;
	private String name;
	private Date dob;
	private String email;
	private Integer phone;
	private String address;
	private List<Elderly> elderlies;

	// Empty constructor
	public Doctor() {
		super();
		elderlies = new ArrayList<Elderly>();
	}

	// Constructor with all the class Task´s attributes as parameters
	public Doctor(String name, int doctor_id, Integer phone, Date dob, String address, List<Elderly> elderlies,
			String email) {
		super();
		this.name = name;
		this.doctor_id = doctor_id;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
		this.elderlies = elderlies;
		this.email = email;
	}

	public Doctor(String name, Integer phone, Date dob, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
	}

	public Doctor(String name, Integer phone, Date dob, String address, String email) {
		this.email = email;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.dob = dob;
	}

	public Doctor(int doctor_id, String name, Date dob, String address, Integer phone, String email) {
		this.doctor_id = doctor_id;
		this.email = email;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.dob = dob;
	}

	// chuleta Doctor doctor = new Doctor(name, phone, dob, address, email);
	//Doctor [name=Pepito, doctor_id=0, email=gmail, phone=34098, dob=0007-05-04, address=avenida]
	public Doctor(String doctor_text) throws ParseException {
		this.name = doctor_text.substring(doctor_text.indexOf("name=") + 5, doctor_text.indexOf(", doctor_id"));
		this.doctor_id = Integer.parseInt(doctor_text.substring(doctor_text.indexOf("tor_id=") + 7, doctor_text.indexOf(", email")));
		this.email = doctor_text.substring(doctor_text.indexOf("email=") + 6, doctor_text.indexOf(", phone"));
		this.phone = Integer.parseInt(doctor_text.substring(doctor_text.indexOf("phone=") + 6, doctor_text.indexOf(", dob")));
		String date_text = "" + doctor_text.substring(doctor_text.indexOf("dob=") + 4, doctor_text.indexOf(", addres"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		this.dob = dateFormat.parse(date_text);

		this.address = doctor_text.substring(doctor_text.indexOf("address=") + 8, doctor_text.indexOf("]"));

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getdoctor_id() {
		return doctor_id;
	}

	public void setdoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Elderly> getElderlies() {
		return elderlies;
	}

	public void setElderlies(List<Elderly> elderlies) {
		this.elderlies = elderlies;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	// Has an equals (uses only name)
	@Override
	public int hashCode() {
		return Objects.hash(doctor_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", doctor_id=" + doctor_id + ", email=" + email + ", phone=" + phone + ", dob="
				+ dob + ", address=" + address + "]";
	}

	public String toStringForPatients() {
		return "ID: " + this.doctor_id + " - " + this.name;
	}

}
