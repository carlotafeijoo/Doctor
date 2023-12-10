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


	public Role(String role_text) {
		super();
		this.id = Integer.parseInt(role_text.substring(role_text.indexOf("id=") + 3, role_text.indexOf(", name")));
		this.name = role_text.substring(role_text.indexOf("name=") + 5, role_text.indexOf("]"));
	}

	/**
	Removes the specified user from the list of users associated with the role.
	@param u the user to be removed
	 */
	public void removeUser(User u) {

		if (users.contains(u)) {
			users.remove(u);
		}

	}
	/**
	 * Retrieves the ID of the role.
	 *
	 * @return the ID of the role
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Sets the ID of the role.
	 *
	 * @param id the ID of the role
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Retrieves the name of the role.
	 *
	 * @return the name of the role
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the role.
	 *
	 * @param name the name of the role
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retrieves the list of users associated with the role.
	 *
	 * @return the list of users associated with the role
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * Sets the list of users associated with the role.
	 *
	 * @param users the list of users associated with the role
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	/**
	 * Retrieves the serial version UID for serialization.
	 *
	 * @return the serial version UID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * Returns a hash code value for the Role object based on the ID and name.
	 *
	 * @return a hash code value for the Role object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	/**
	 * Indicates whether some other object is "equal to" this one based on the ID and name.
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
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	/**
	 * Adds the specified user to the list of users associated with the role.
	 *
	 * @param u the user to be added
	 */
	public void addUser(User u) {
		if (!users.contains(u)) {
			users.add(u);
		}

	}
	/**
	 * Returns a string representation of the Role object.
	 *
	 * @return a string representation of the Role object
	 */
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}


}