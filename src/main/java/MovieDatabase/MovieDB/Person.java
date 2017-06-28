package MovieDatabase.MovieDB;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String role_flag;

	@Id
	@GeneratedValue
	private int id;

	public Person() {
	};

	public Person(String firstname, String lastname, String username) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.role_flag = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRoleFlag() {
		return role_flag;
	}

	public void setRoleFlag(String role_flag) {
		this.role_flag = role_flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}