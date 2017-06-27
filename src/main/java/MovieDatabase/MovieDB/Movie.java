package MovieDatabase.MovieDB;

public class Movie {

	String movieName;
	String director;
	
	
	public Movie(String movieName, String director) {
		super();
		this.movieName = movieName;
		this.director = director;
	}

	public Movie() {  //needed for POST to send back as a movie object
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
}
