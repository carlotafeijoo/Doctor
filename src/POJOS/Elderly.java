package POJOS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Elderly")
@XmlType(propOrder= {"name" , "age"})
public class Elderly implements Serializable{
	
	
	
	//pkepokrehpok
	
	private static final long serialVersionUID = -8265618237491167237L;
	
	@XmlTransient
	private int elderly_id;
	@XmlAttribute
	private String name;
	@XmlElement
	private int age;
	@XmlTransient
	private List<Staff> staffmembers;
	
	public Elderly() {
		super();
		setStaffmembers(new ArrayList<Staff>());
	}
	
	public Elderly(int elderly_id, String name, int age) {
		super();
		this.elderly_id = elderly_id;
		this.name = name;
		this.age = age;
	} 
	
	public Elderly( String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Staff> getStaffmembers() {
		return staffmembers;
	}

	public void setStaffmembers(List<Staff> staffmembers) {
		this.staffmembers = staffmembers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "\n [Elderly [elderly_id=" + elderly_id + ", name=" + name + ", age=" + age + ", staffmembers="
				+ staffmembers + "]]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, elderly_id, name);
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
		return age == other.age && elderly_id == other.elderly_id && Objects.equals(name, other.name);
	}	

}

