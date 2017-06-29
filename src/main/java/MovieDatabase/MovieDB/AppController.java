package MovieDatabase.MovieDB;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web. bind.annotation.RequestBody;

@RestController
public class AppController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private personRepository personRepository;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		model.addAttribute("movieName", session.getAttribute("movieName"));
		model.addAttribute("director", session.getAttribute("director"));
		return "movies";
	}

	@RequestMapping(path = "/api/movies/{id}", method = RequestMethod.GET)
	public Movie getmovie(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) int id) {
		return movieRepository.findById(id);
	}

	@RequestMapping(path = "/api/movies", method = RequestMethod.POST)
	public Movie postmovie(Model model, HttpSession session, String movieName,
			String director, ArrayList<Person> personlist) {
		Movie n = new Movie(movieName, director);
		if (personlist != null) {
			for (Person i : personlist) {
				n.addPerson(i);
			}
		}
		movieRepository.save(n);
		return n;
	}

	@RequestMapping(path = "/api/movies", method = RequestMethod.PUT)
	public void putmovie(Model model, HttpSession session, String movieName,
			String director, int movie_id, String person_id) {
		Movie n = movieRepository.findById(movie_id);
		if (movieName != null) {
			n.setMovieName(movieName);
		}
		if (director != null) {
			n.setDirector(director);
		}
		if (person_id!=null){
			Person p = personRepository.findOne(Integer.parseInt(person_id));
			n.addPerson(p);
		}
		movieRepository.save(n);
	}

	@RequestMapping(path = "/api/movies", method = RequestMethod.DELETE)
	public void deletemovie(Model model, HttpSession session, int id) {
		movieRepository.delete(id);
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.POST)
	public List<User> user(Model model, HttpSession session, String userName,
			String firstName, String lastName) {
		User user = new User(userName, firstName, lastName);
		userRepository.save(user);
		session.setAttribute("userName", userName);
		session.setAttribute("firstName", userName);
		session.setAttribute("lastName", userName);

		// return movieRepository.findAll();
		return userRepository.findAll();
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.GET)
	public List<User> getusers(Model model, HttpSession session) {

		return userRepository.findAll();
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.DELETE)
	public List<User> user(Model model, HttpSession session, int id) {
		userRepository.delete(id);
		return userRepository.findAll();
	}

	@RequestMapping(path = "/addmovie", method = RequestMethod.POST)
	public String addmovie(@RequestBody Movie m) {
		// save the joke
		Movie n = new Movie(m.getMovieName(), m.getDirector());
		movieRepository.save(n);

		return m.getDirector();
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

	@RequestMapping(path = "/api/person", method = RequestMethod.POST)

	public List<Person> person(Model model, HttpSession session,
			String firstname, String lastname, String role_flag) {
		Person p = new Person(firstname, lastname, role_flag);
		personRepository.save(p);

		return personRepository.findAll();
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

	/*
	 * @RequestMapping(path = "/addmovie", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Movie addmovie(Model model, HttpSession session,
	 * String userName, String director) {
	 * 
	 * Movie m = new Movie(userName, director); return m; }
	 */

	@RequestMapping(path = "/getmovie", method = RequestMethod.GET) // Get or
																	// Post?
	@ResponseBody
	public void getmovie(Model model, HttpSession session, String director,
			String actor, String title, String genre) {
		if (director == null) {
			director = "";
		}
		if (actor == null) {
			actor = "";
		}
		if (title == null) {
			title = "";
		}
		if (genre == null) {
			genre = "";
		}

		// query DB
		// return a path with data?
	}

}