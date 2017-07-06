package MovieDatabase.MovieDB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

Movie findById(int id);
@Query("select m from Movie m where id = ?1")
Movie findByMovieName(String movie_name);
}
