package MovieDatabase.MovieDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONController {
	
	@RequestMapping(path = "/movie.json", method = RequestMethod.POST)
	public Movie jsonHome(String movieName, String year) {
		
		return new Movie(movieName, year);
	}

}