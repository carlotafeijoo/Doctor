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

	/**
	 * Default constructor for the Elderly class.
	 * Initializes the list of doctors as an empty ArrayList.
	 */
	public Elderly() {
		super();
		setDoctor_list(new ArrayList<Doctor>());
	}
	/**
	 * Constructor for the Elderly class with parameters.
	 * 
	 * @param elderly_id the ID of the elderly
	 * @param name the name of the elderly
	 * @param dni the DNI of the elderly
	 * @param dob the date of birth of the elderly
	 * @param doctor_id the ID of the doctor associated with the elderly
	 */
	public Elderly(int elderly_id, String name, int dni, Date dob, int doctor_id) {
		super();
		this.elderly_id = elderly_id;
		this.dni = dni;
		this.name = name;
		this.dob = dob;
		this.doctor_id = doctor_id;
	}
	/**
	 * Constructor for the Elderly class with basic information.
	 * 
	 * @param elderly_id the ID of the elderly
	 * @param name the name of the elderly
	 * @param dni the DNI of the elderly
	 * @param dob the date of birth of the elderly
	 */
	public Elderly(int elderly_id, String name, int dni, Date dob) {
		super();
		this.elderly_id = elderly_id;
		this.dni = dni;
		this.name = name;
		this.dob = dob;
	}
	/**
	 * Constructor for the Elderly class with basic information and the ID of the associated doctor.
	 * 
	 * @param name the name of the elderly
	 * @param dni the DNI of the elderly
	 * @param dob the date of birth of the elderly
	 * @param doctor_id the ID of the doctor associated with the elderly
	 */
	public Elderly(String name, int dni, Date dob, int doctor_id) {
		super();
		this.dni = dni;
		this.name = name;
		this.dob = dob;
		this.doctor_id = doctor_id;
	}
	/**
	 * Constructor for the Elderly class with essential information.
	 * 
	 * @param name the name of the elderly
	 * @param dob the date of birth of the elderly
	 */
	public Elderly(String name, Date dob) {
		super();
		this.name = name;
		this.dob = dob;
	}
	/**
	 * Constructor for the Elderly class that takes a string representation of an elderly person's information.
	 * 
	 * @param elderly_text the string representation of an elderly person's information
	 * @throws ParseException if an error occurs during the parsing of the date
	 */

	public Elderly(String elderly_text) throws ParseException {
		this.elderly_id = Integer.parseInt(elderly_text.substring(elderly_text.indexOf("elderly_id=") +11, elderly_text.indexOf(", name")));
		this.name = elderly_text.substring(elderly_text.indexOf("name=") + 5, elderly_text.indexOf(", dni"));
		this.dni = Integer.parseInt(elderly_text.substring(elderly_text.indexOf("dni=") +4, elderly_text.indexOf(", doctor_id")));
		this.doctor_id = Integer.parseInt(elderly_text.substring(elderly_text.indexOf("doctor_id=") +10, elderly_text.indexOf(", dob")));

		String date_text = "" + elderly_text.substring(elderly_text.indexOf("dob=") + 4, elderly_text.indexOf("symptoms="));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.dob = dateFormat.parse(date_text);
		this.symptoms = elderly_text.substring(elderly_text.indexOf("symptoms=") + 9, elderly_text.indexOf("]"));

	}
	/**
	 * Constructor for the Elderly class with parameters.
	 * 
	 * @param elderly_id the ID of the elderly
	 * @param name the name of the elderly
	 * @param dni the DNI of the elderly
	 * @param doctor_id the ID of the doctor associated with the elderly
	 * @param dob the date of birth of the elderly
	 * @param symptoms the symptoms of the elderly
	 * @param doctor_list the list of doctors associated with the elderly
	 */

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
	/**
	 * Constructor for the Elderly class with essential information and symptoms.
	 * 
	 * @param elderly_id the ID of the elderly
	 * @param name the name of the elderly
	 * @param dni the DNI of the elderly
	 * @param doctor_id the ID of the doctor associated with the elderly
	 * @param dob the date of birth of the elderly
	 * @param symptoms the symptoms of the elderly
	 */
	public Elderly(int elderly_id, String name, int dni, int doctor_id, Date dob, String symptoms) {
		super();
		this.elderly_id = elderly_id;
		this.name = name;
		this.dni = dni;
		this.doctor_id = doctor_id;
		this.dob = dob;
		this.symptoms = symptoms;

	}
	/**
	 * Constructor for the Elderly class with essential information and symptoms.
	 * 
	 * @param elderly_id the ID of the elderly
	 * @param name the name of the elderly
	 * @param dni the DNI of the elderly
	 * @param dob the date of birth of the elderly
	 * @param symptoms the symptoms of the elderly
	 */
	public Elderly(int elderly_id, String name, int dni, Date dob, String symptoms) {
		super();
		this.elderly_id = elderly_id;
		this.name = name;
		this.dni = dni;
		this.dob = dob;
		this.symptoms = symptoms;

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
	Retrieves the name of the elderly person.
	@return the name of the elderly person
	 */
	public String getName() {
		return name;
	}
	/**
	Sets the name of the elderly person.
	@param name the name of the elderly person
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	Retrieves the date of birth of the elderly person.
	@return the date of birth of the elderly person
	 */
	public Date getDob() {
		return dob;
	}
	/**
	Sets the date of birth of the elderly person.
	@param dob the date of birth of the elderly person
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	Retrieves the list of doctors associated with the elderly person.
	@return the list of doctors associated with the elderly person
	 */
	public List<Doctor> getDoctor_list() {
		return doctor_list;
	}
	/**
	Sets the list of doctors associated with the elderly person.
	@param doctor_list the list of doctors associated with the elderly person
	 */
	public void setDoctor_list(List<Doctor> doctor_list) {
		this.doctor_list = doctor_list;
	}
	/**
	Retrieves the serial version UID for serialization.
	@return the serial version UID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	Returns a string representation of the Elderly object.
	@return a string representation of the Elderly object
	 */
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "Elderly [elderly_id=" + elderly_id + ", name=" + name + ", dni=" + dni + ", doctor_id=" + doctor_id
				+ ", dob=" + dob + ", symptoms=" + symptoms + "]";
	}

	/**
	Retrieves the DNI (National Identity Document) of the elderly person.
	@return the DNI of the elderly person
	 */
	public int getDni() {
		return dni;
	}

	/**
	Sets the DNI (National Identity Document) of the elderly person.
	@param dni the DNI of the elderly person
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}
	/**
	Returns a hash code value for the Elderly object based on the elderly_id.
	@return a hash code value for the Elderly object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(elderly_id);
	}
	/**
	Retrieves the symptoms exhibited by the elderly person.
	@return the symptoms exhibited by the elderly person
	 */
	public String getSymptoms() {
		return symptoms;
	}
	/**
	Sets the symptoms exhibited by the elderly person.
	@param symptoms the symptoms exhibited by the elderly person
	 */
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	/**
	Retrieves the unique identifier of the doctor associated with the elderly person.
	@return the unique identifier of the doctor associated with the elderly person
	 */
	public int getDoctor_id() {
		return doctor_id;
	}
	/**
	Sets the unique identifier of the doctor associated with the elderly person.
	@param doctor_id the unique identifier of the doctor associated with the elderly person
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	/**
	Retrieves the list of tasks associated with the elderly person.
	@return the list of tasks associated with the elderly person
	 */
	public List<Task> getTask_list() {
		return task_list;
	}
	/**
	Sets the list of tasks associated with the elderly person.
	@param task_list the list of tasks associated with the elderly person
	 */
	public void setTask_list(List<Task> task_list) {
		this.task_list = task_list;
	}
	/**
	Indicates whether some other object is "equal to" this one.
	@param obj the reference object with which to compare
	@return true if this object is the same as the obj argument; false otherwise
	 */
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
