package MovieDatabase.MovieDB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface personRepository extends JpaRepository<Person, Integer> {
//@Query("select m from Movie m where movieName = ?1 and director = ?2")
//List<Movie> findBYmovieName(String movieName, String director);
}