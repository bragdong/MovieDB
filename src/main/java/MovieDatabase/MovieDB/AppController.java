package MovieDatabase.MovieDB;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AppController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private personRepository personRepository;
	
	// @RequestMapping(path = "/person", method = RequestMethod.GET)
	// public String person(Model model, String name, String city, int age) {
	// Person p = new Person(name, city, age);
	// model.addAttribute("person", p);
	// return "person";
	// }

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		model.addAttribute("movieName", session.getAttribute("movieName"));
		model.addAttribute("director", session.getAttribute("director"));
		return "movies";
	}

	@RequestMapping(path = "/movies", method = RequestMethod.POST)
	public List<Movie> movie(Model model, HttpSession session, String movieName,
			String director) {
		Movie n = new Movie(movieName, director);
		movieRepository.save(n);
		//Movie m = movieRepository.findOne(2);
		//System.out.println(m.getMovieName());
		session.setAttribute("movieName", movieName);
		session.setAttribute("director", director);
		//return movieRepository.findAll();
		return movieRepository.findBYmovieName("IndianaJones2", "stanley67");
	}
	@RequestMapping(path = "/addmovie", method = RequestMethod.POST)
	public String addmovie(@RequestBody Movie m) {
		// save the joke
		Movie n = new Movie(m.getMovieName(), m.getDirector());
		movieRepository.save(n);

		return m.getDirector();
	}
	
	@RequestMapping(path = "/person", method = RequestMethod.POST)
	public List<Person> person(Model model, HttpSession session, String firstname,
			String lastname, String role_flag) {
		Person p = new Person(firstname, lastname, role_flag);
		personRepository.save(p);
		//Movie m = movieRepository.findOne(2);
		//System.out.println(m.getMovieName());
		//return movieRepository.findAll();
		return personRepository.findAll();
	}
	
	
	
	
/*	@RequestMapping(path = "/addmovie", method = RequestMethod.POST)
	@ResponseBody
	public Movie addmovie(Model model, HttpSession session, String userName,
			String director) {

		Movie m = new Movie(userName, director);
		return m;
	}*/

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