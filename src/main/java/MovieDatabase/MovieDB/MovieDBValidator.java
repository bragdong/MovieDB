package MovieDatabase.MovieDB;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javassist.tools.web.Webserver;

@Component("beforeCreateWebsiteUserValidator")
public class MovieDBValidator implements Validator {
	 
    @Override
    public boolean supports(Class<?> clazz) {
        return Webserver.class.equals(clazz);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        if (checkInputString(user.getFirstName())) {
            errors.rejectValue("firstName", "firstName.empty");
        }
        if (checkInputString(user.getLastName())) {
            errors.rejectValue("lastName", "lastName.empty");
        }

    }
 
    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
