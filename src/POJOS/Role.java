package POJOS;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "Role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5726931783696626202L;

	@Id
	@GeneratedValue(generator = "Role")
	@TableGenerator(name = "Role", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Role")
	private Integer id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users;

	public Role() {
		super();
	}

	/*public Role(String name) {
		super();
		this.name = name;
	}*/
	
	//"Role [id=" + id + ", name=" + name + "]"
	public Role(String role_text) {
		super();
		this.id = Integer.parseInt(role_text.substring(role_text.indexOf("id=") + 3, role_text.indexOf(", name")));
		this.name = role_text.substring(role_text.indexOf("name=") + 5, role_text.indexOf("]"));
	}

	public void removeUser(User u) {

		if (users.contains(u)) {
			users.remove(u);
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public void addUser(User u) {
		if (!users.contains(u)) {
			users.add(u);
		}

	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	
}