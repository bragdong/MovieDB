package MovieDatabase.MovieDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movie")

public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieName=" + movieName + ", director="
				+ director + ", personlist=" + personlist + "]";
	}

	@Id
	@GeneratedValue
	private int id;

	private String movieName;
	private String director;
	 @ManyToMany(mappedBy = "movielist")
	 
	private List<Person> personlist;

	public List<Person> getPersonlist() {
		return personlist;
	}

	public void setPersonlist(List<Person> personlist) {
		this.personlist = personlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie(String movieName, String director) {
		super();
		this.movieName = movieName;
		this.director = director;
	}

	public Movie() { // needed for POST to send back as a movie object
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public void addPerson(Person p) {
		if (!personlist.contains(p))
		{this.personlist.add(p);}
	}
}
