package POJOS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name="Staff")
//@XmlType(propOrder= {"name","phone", "dob", "address"})

public class Doctor {
	

	//@XmlTransient //this means i dont want the id to be shown in xml
	private int doctor_id;
	//@XmlAttribute
	private String name;
	//@XmlElement
	private Date dob;
	//@XmlTransient
	private String email;
	//@XmlElement
	private Integer phone;
	//@XmlElement
	private String address;
	//@XmlTransient 
	private List<Elderly> elderlies; // Many to many relationship


	//Empty constructor
	public Doctor() {
		super();
		elderlies= new ArrayList<Elderly>();
	}


	//Constructor with all the class Task´s attributes as parameters
	public Doctor(String name, int doctor_id, Integer phone, Date dob, String address, 
			List<Elderly> elderlies, String email) {
		super();
		this.name = name;
		this.doctor_id = doctor_id;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
		this.elderlies = elderlies;
		this.email=email;
	}
	
	public Doctor(String name, Integer phone, Date dob, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.dob = dob;
		this.address = address;
	}


	public Doctor(String name, Integer phone, Date dob, String address, String email) {
		this.email=email;
		this.address=address;
		this.name=name;
		this.phone=phone;
		this.dob=dob;
	}
	
	

	public Doctor(int doctor_id, String name, Date dob, String address, Integer phone, String email) {
		this.doctor_id=doctor_id;
		this.email=email;
		this.address=address;
		this.name=name;
		this.phone=phone;
		this.dob=dob;
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
		this.email=email;
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
		return "\n [Staff [name=" + name + ", doctor_id=" + doctor_id + ", email=" + email + ", phone="
				+ phone + ", dob=" + dob + ", address=" + address + ", elderlies=" + elderlies + "]]";
	}
	
	


	
	
}

