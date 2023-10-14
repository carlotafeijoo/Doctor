package POJOS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name="Elderly")
//@XmlType(propOrder= {"name" , "age"})
public class Elderly implements Serializable{
	
	private static final long serialVersionUID = -8265618237491167237L;
	
	//@XmlTransient
	private int elderly_id;
	//@XmlAttribute
	private String name;
	//@XmlElement
	private Date dob;
	//@XmlTransient
	private List<Doctor> doctor_list;
	
	public Elderly() {
		super();
		setDoctor_list(new ArrayList<Doctor>());
	}
	
	//pendiente anadir constructores
	
	public Elderly(int elderly_id, String name, Date dob) {
		super();
		this.elderly_id = elderly_id;
		this.name = name;
		this.dob = dob;
	} 
	
	public Elderly( String name, Date dob) {
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
		return "\n [Elderly [elderly_id=" + elderly_id + ", name=" + name + ", date of birth=" + dob + ", doctor_list="
				+ doctor_list + "]]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(elderly_id);
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

