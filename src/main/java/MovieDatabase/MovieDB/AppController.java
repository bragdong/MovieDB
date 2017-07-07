package MovieDatabase.MovieDB;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

	// @RequestMapping(path = "/", method = RequestMethod.GET)
	// public String home(Model model, HttpSession session) {
	// model.addAttribute("movieName", session.getAttribute("movieName"));
	// model.addAttribute("year", session.getAttribute("year"));
	// return "movies";
	// }

	@RequestMapping(path = "/api/movies/{id}", method = RequestMethod.GET)
	public Movie getmovie(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) int id) {
		
		return movieRepository.findById(id);
	}

	@RequestMapping(path = "/api/movies", method = RequestMethod.POST)
	public Movie postmovie(@RequestBody Movie m) {
		movieRepository.save(m);
		return m;
	}
	// {"movieName":"JAWS7",
	// "year":"1982",
	// "personlist":[{"firstname":"jonathan", "lastname":"shales"},
	// {"firstname":"john", "lastname":"shales"}]
	// }

	@RequestMapping(path = "/api/movies", method = RequestMethod.PUT)
	public void putmovie(Model model, HttpSession session, String movieName,
			String year, int movie_id, String person_id) {
		Movie n = movieRepository.findById(movie_id);
		//System.out.println(n.toString());
		if (movieName != null) {
			n.setMovieName(movieName);
		}
		if (year != null) {
			n.setYear(year);
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
	public List<User> getusers(Model model, HttpSession session) {

		return userRepository.findAll();
	}

	@RequestMapping(path = "/api/user/{id}", method = RequestMethod.GET)
	public User getUser(Model model, HttpSession session,
			@PathVariable(name = "id", required = true) int id) {
		return userRepository.findOne(id);
	}	
	
	@RequestMapping(path = "/api/user", method = RequestMethod.POST)
	public void addUser(@RequestBody User newUser) {
		userRepository.save(newUser);
	}

	@RequestMapping(path = "/api/user", method = RequestMethod.PUT)
	public User updateuser(Model model, HttpSession session,
			@RequestBody User user) {
		User existing = userRepository.findOne(user.getUserId());
		existing.merge(user);
		userRepository.save(existing);
		return existing;
	}

//	@RequestMapping(path = "/api/user", method = RequestMethod.DELETE)
//	public void delUser(Model model, HttpSession session, int id) {
//		userRepository.delete(id);
//	}

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
	// {"firstname":"Jeremy4",
	// "lastname":"Speilberg22",
	// "role_flag":"actor"
	// }
	public Person person(@RequestBody Person p) {
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

	// Adding CRUD operations for Ratings but commented out for now until we
	// discuss
	// @RequestMapping(path = "/api/rating/{id}", method = RequestMethod.GET)
	// public Rating getRating(Model model, HttpSession session,
	// @PathVariable(name = "id", required = true) int id) {
	// return ratingRepository.findOne(id);
	// }
	//
	// @RequestMapping(path = "/api/rating", method = RequestMethod.POST)
	//
	// public List<Rating> person(Model model, HttpSession session,
	// Movie movie, User user, int rating) {
	// Rating r = new Rating(movie, user, rating);
	// ratingRepository.save(r);
	//
	// return ratingRepository.findAll();
	// }
	//
	// @RequestMapping(path = "/api/rating", method = RequestMethod.PUT)
	// public Person updateperson(Model model, HttpSession session,
	// @RequestBody Rating rating) {
	// Rating existing = ratingRepository.findOne(rating.getId());
	// existing.merge(rating);
	// ratingRepository.save(existing);
	// return existing;
	// }
	//
	// @RequestMapping(path = "/api/rating", method = RequestMethod.DELETE)
	// public List<rating> person(Model model, HttpSession session, int id) {
	// ratingRepository.delete(id);
	// return ratingRepository.findAll();
	// }

	/*
	 * @RequestMapping(path = "/addmovie", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Movie addmovie(Model model, HttpSession session,
	 * String userName, String year) {
	 * 
	 * Movie m = new Movie(userName, year); return m; }
	 */

	@RequestMapping(path = "/getmovie", method = RequestMethod.GET) // Get or
																	// Post?
	@ResponseBody
	public void getmovie(Model model, HttpSession session, String year,
			String actor, String title, String genre) {
		if (year == null) {
			year = "";
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