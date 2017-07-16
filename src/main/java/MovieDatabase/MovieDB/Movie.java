package MovieDatabase.MovieDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Entity
@Table(name = "movie")

public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieName=" + movieName + ", year=" + year
				+ ", personlist=" + personlist + "]";
	}

	@Id
	@GeneratedValue
	int id;

	@NotNull(groups = New.class)
	@Size(min=1, max=35)
	private String movieName;
	@NotNull(groups = New.class)
	private String year;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Person> personlist;

	public Set<Person> getPersonlist() {
		return personlist;
	}

	public void setPersonlist(Set<Person> personlist) {
		this.personlist = personlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie(String movieName, String year) {
		super();
		this.movieName = movieName;
		this.year = year;
	}

	public Movie() {
		this.personlist = new HashSet<Person>();
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void addPerson(Person p) {
		if (!personlist.contains(p)) {
			this.personlist.add(p);
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


    public interface Existing {
    }
 
    public interface New {
    }
	
}
