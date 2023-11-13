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

	public User(Integer id, String username, byte[] password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(String username, byte[] password) {
		super();
		this.username = username;
		this.password = password;
	}

	//"User [id=" + id + ", username=" + username + ", role=" + role + "]"
	public User(String user_text) {
		super();
		this.id = Integer.parseInt(user_text.substring(user_text.indexOf("id=") + 3, user_text.indexOf(", username")));
		this.username = user_text.substring(user_text.indexOf("username=") + 9, user_text.indexOf("]"));

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(username, id, role);
		return result;
	}

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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
	}


}
