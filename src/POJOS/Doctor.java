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


	/**
	Default constructor for the Doctor class.
	Initializes the list of elderlies as an empty ArrayList.
	*/	public Doctor() {
		super();
		elderlies = new ArrayList<>();
	}

	/**
	Constructor for the Doctor class with parameters.
	@param name the name of the doctor
	@param doctor_id the ID of the doctor
	@param phone the phone number of the doctor
	@param dob the date of birth of the doctor
	@param address the address of the doctor
	@param elderlies the list of elderlies associated with the doctor
	@param email the email of the doctor
	*/
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
	/**
	Constructor for the Doctor class with basic information.
	@param name the name of the doctor
	@param phone the phone number of the doctor
	@param dob the date of birth of the doctor
	@param address the address of the doctor
	*/
	public Doctor(String name, Integer phone, Date dob, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
	}
	/**
	 * Constructor for the Doctor class with basic information and email.
	 * 
	 * @param name the name of the doctor
	 * @param phone the phone number of the doctor
	 * @param dob the date of birth of the doctor
	 * @param address the address of the doctor
	 * @param email the email of the doctor
	 */
	public Doctor(String name, Integer phone, Date dob, String address, String email) {
		this.email = email;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.dob = dob;
	}
	/**
	 * Constructor for the Doctor class with complete information.
	 * 
	 * @param doctor_id the ID of the doctor
	 * @param name the name of the doctor
	 * @param dob the date of birth of the doctor
	 * @param address the address of the doctor
	 * @param phone the phone number of the doctor
	 * @param email the email of the doctor
	 */
	public Doctor(int doctor_id, String name, Date dob, String address, Integer phone, String email) {
		this.doctor_id = doctor_id;
		this.email = email;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.dob = dob;
	}
	/**
	 * Constructor for the Doctor class that takes a string representation of a doctor's information.
	 * 
	 * @param doctor_text the string representation of a doctor's information
	 * @throws ParseException if an error occurs during the parsing of the date
	 */
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
	/**
	 * Retrieves the name of the doctor.
	 * 
	 * @return the name of the doctor
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the doctor.
	 * 
	 * @param name the name of the doctor
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retrieves the ID of the doctor.
	 * 
	 * @return the ID of the doctor
	 */
	public int getdoctor_id() {
		return doctor_id;
	}
	/**
	 * Sets the ID of the doctor.
	 * 
	 * @param doctor_id the ID of the doctor
	 */
	public void setdoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	/**
	 * Retrieves the date of birth of the doctor.
	 * 
	 * @return the date of birth of the doctor
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * Sets the date of birth of the doctor.
	 * 
	 * @param dob the date of birth of the doctor
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * Retrieves the address of the doctor.
	 * 
	 * @return the address of the doctor
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Retrieves the phone number of the doctor.
	 * 
	 * @return the phone number of the doctor
	 */
	public Integer getPhone() {
		return phone;
	}
	/**
	 * Sets the address of the doctor.
	 * 
	 * @param address the address of the doctor
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Retrieves the list of elderlies associated with the doctor.
	 * 
	 * @return the list of elderlies associated with the doctor
	 */
	public List<Elderly> getElderlies() {
		return elderlies;
	}
	/**
	 * Sets the list of elderlies associated with the doctor.
	 * 
	 * @param elderlies the list of elderlies associated with the doctor
	 */
	public void setElderlies(List<Elderly> elderlies) {
		this.elderlies = elderlies;
	}
	/**
	 * Sets the phone number of the doctor.
	 * 
	 * @param phone the phone number of the doctor
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	/**
	 * Sets the email of the doctor.
	 * 
	 * @param email the email of the doctor
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Retrieves the email of the doctor.
	 * 
	 * @return the email of the doctor
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns a hash code value for the Doctor object based on the doctor_id.
	 * 
	 * @return the hash code value for the Doctor object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(doctor_id);
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
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
		Doctor other = (Doctor) obj;
		return Objects.equals(name, other.name);
	}

	/**
	 * Returns a string representation of the Doctor object.
	 * 
	 * @return a string representation of the Doctor object
	 */
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "Doctor [name=" + name + ", doctor_id=" + doctor_id + ", email=" + email + ", phone=" + phone + ", dob="
				+ dateFormat.format(dob) + ", address=" + address + "]";
	}

	/**
	 * Returns a string representation of the Doctor object for patients.
	 * 
	 * @return a string representation of the Doctor object for patients
	 */
	public String toStringForPatients() {
		return "ID: " + this.doctor_id + " - " + this.name;
	}

}
