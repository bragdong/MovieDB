package MovieDatabase.MovieDB;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
//	@RequestMapping(path = "/person", method = RequestMethod.GET)
//	public String person(Model model, String name, String city, int age) {
//		Person p = new Person(name, city, age);
//		model.addAttribute("person", p);
//		return "person";
//	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		model.addAttribute("movieName", session.getAttribute("movieName"));
		model.addAttribute("director", session.getAttribute("director"));
		return "movies";
	}
	
	@RequestMapping(path = "/movies", method = RequestMethod.POST)
	public String movie(Model model, HttpSession session, String movieName, String director) {
		System.out.println(movieName);
		session.setAttribute("movieName", movieName);
		session.setAttribute("director", director);
		return "movie.json";
	}  

}