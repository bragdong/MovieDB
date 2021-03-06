package MovieDatabase.MovieDB;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import MovieDatabase.MovieDB.Movie.Existing;
import MovieDatabase.MovieDB.Movie.New;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AppController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private personRepository personRepository;
	
	@Autowired
	private MovieDBValidator MovieDBValidator;

	@RequestMapping(path = "/api/movies/{id}", method = RequestMethod.GET)
	public Movie getmovie(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) int id) {

		return movieRepository.findById(id);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully added movie") })
	@ApiOperation(value = "Movies Post", notes = "This allows a user to add a movie to the database.")
	@RequestMapping(path = "/api/movies", method = RequestMethod.POST)
	public Movie postmovie(@Validated({New.class}) @RequestBody Movie m) {
		movieRepository.save(m);
		return m;
	}

	@RequestMapping(path = "/api/movies", method = RequestMethod.PUT)
	public void putmovie(@Validated({Existing.class}) @RequestBody Movie m, String person_id) {
		Movie n = movieRepository.findById(m.getId());
		if (m.getMovieName() != null) {
			n.setMovieName(m.getMovieName());
		}
		if (m.getYear() != null) {
			n.setYear(m.getYear());
		}
		if (person_id != null) {
			Person p = personRepository.findOne(Integer.parseInt(person_id));
			System.out.println(p.toString());
			n.getPersonlist().add(p);
		}
		movieRepository.save(n);
	}

	@RequestMapping(path = "/api/movies", method = RequestMethod.DELETE)
	public void deletemovie(Model model, HttpSession session, int id) {
		movieRepository.delete(id);
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.GET)
	@ApiOperation(value = "Get User", notes = "Returns a list of all users in the database.")
	public List<User> getusers(Model model, HttpSession session) {

		return userRepository.findAll();
	}

	@RequestMapping(path = "/api/user/{id}", method = RequestMethod.GET)
	public User getUser(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) int id) {
		return userRepository.findOne(id);
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.POST)
	public User addUser(@RequestBody User newUser) {
		userRepository.save(newUser);
		return newUser;
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.PUT)
	public User updateuser(Model model, HttpSession session,
			@RequestBody User user) {
		User existing = userRepository.findOne(user.getUserId());
		existing.merge(user);
		userRepository.save(existing);
		return existing;
	}

	@RequestMapping(path = "/api/user/{id}", method = RequestMethod.DELETE)
	public void deleteUser(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) Integer id) {
		userRepository.delete(id);
	}

	@RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET)
	public Person getPerson(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) int id) {
		return personRepository.findOne(id);
	}

	@RequestMapping(path = "/api/person", method = RequestMethod.GET)
	public List<Person> getpeople(Model model, HttpSession session) {
		return personRepository.findAll();
	}

	@RequestMapping(path = "/api/person/", method = RequestMethod.POST)
	public Person person(@RequestBody @Valid Person p) {
		personRepository.save(p);
		return p;
	}

	@RequestMapping(path = "/api/person", method = RequestMethod.PUT)
	public Person updateperson(Model model, HttpSession session,
			@RequestBody Person person) {
		Person existing = personRepository.findOne(person.getId());
		existing.merge(person);
		personRepository.save(existing);
		return existing;
	}

	@RequestMapping(path = "/api/person", method = RequestMethod.DELETE)
	public List<Person> person(Model model, HttpSession session, int id) {
		personRepository.delete(id);
		return personRepository.findAll();
	}

}