package MovieDatabase.MovieDB;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int userId;
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	

	@OneToMany(cascade = CascadeType.ALL)  // User has one to many ratings
	@JoinColumn(name = "rating_id")
	private List<Rating> ratings;    // why do we need to have this on user if there can be rows on rating table related to a user?
	
	public User(String userName, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = "";
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void merge(User other) {
		if (other.userName != null) {
			this.userName = other.userName;
		}
		if (other.firstName != null) {
			this.firstName = other.firstName;
		}
		if (other.lastName != null) {
			this.lastName = other.lastName;
		}
		if (other.ratings != null) {
			this.ratings = other.ratings;
		}
	}	
	
}