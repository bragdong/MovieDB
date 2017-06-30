package MovieDatabase.MovieDB;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating")

public class Rating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;  
	
	@ManyToOne  // There can be many ratings for a single movie
	private Movie movie;
	
	@ManyToOne  // There can be many ratings for a single user
	private User user;
	
	private	int rating;
	
	public Rating(Movie movie, User user, int rating) {
		super();
		this.movie =  movie;
		this.rating = rating;
	}

	public Rating() {
		super();
	}

	public int getRatingId() {
		return id;
	}

	public void setRatingId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {   // Is this needed ??
		return serialVersionUID;
	}	

	// Do we need methods here to add a Movie and User to Rating object?
}
