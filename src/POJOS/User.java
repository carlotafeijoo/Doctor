package POJOS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "User")

public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2078397574496562875L;

	@Id
	@GeneratedValue(generator = "User")
	@TableGenerator(name = "User", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "User")
	private Integer id;
	private String username;
	@Lob
	private byte[] password;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {
		super();
	}
	/**
	 * Constructs a User object with the specified ID, username, password, and role.
	 *
	 * @param id the unique identifier for the user
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param role the role of the user
	 */
	public User(Integer id, String username, byte[] password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	/**
	 * Constructs a User object with the specified username and password.
	 *
	 * @param username the username of the user
	 * @param password the password of the user
	 */
	public User(String username, byte[] password) {
		super();
		this.username = username;
		this.password = password;
	}
	/**
	 * Constructs a User object from the provided string representation.
	 *
	 * @param user_text the string representation of the User object
	 */
	public User(String user_text) {
		super();
		this.id = Integer.parseInt(user_text.substring(user_text.indexOf("id=") + 3, user_text.indexOf(", username")));
		this.username = user_text.substring(user_text.indexOf("username=") + 9, user_text.indexOf("]"));

	}
	/**
	 * Retrieves the ID of the user.
	 *
	 * @return the ID of the user
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Sets the ID of the user.
	 *
	 * @param id the ID of the user
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Retrieves the username of the user.
	 *
	 * @return the username of the user
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets the username of the user.
	 *
	 * @param username the username of the user
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Retrieves the password of the user.
	 *
	 * @return the password of the user
	 */
	public byte[] getPassword() {
		return password;
	}
	/**
	 * Sets the password of the user.
	 *
	 * @param password the password of the user
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}
	/**
	 * Retrieves the role of the user.
	 *
	 * @return the role of the user
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * Sets the role of the user.
	 *
	 * @param role the role of the user
	 */
	public void setRole(Role role) {
		this.role = role;
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
	 * Returns a hash code value for the User object.
	 *
	 * @return a hash code value for the User object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(username, id, role);
		return result;
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
		User other = (User) obj;
		return Objects.equals(username, other.username) && Objects.equals(id, other.id)
				&& Arrays.equals(password, other.password) && Objects.equals(role, other.role);
	}
	/**
	 * Returns a string representation of the User object.
	 *
	 * @return a string representation of the User object
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
	}


}
