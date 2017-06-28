package MovieDatabase.MovieDB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
@Query("select user from User user where userName = ?1")
List<User> findBYuserName(String userName);
}
