package MovieDatabase.MovieDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieDbApplicationTests {

    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
	private MovieRepository movieRepository;

    private MockMvc mockMvc;
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        }
	
	@Test
	public void contextLoads() throws Exception {
		Movie m = new Movie("TEST", "1982");
        String json = new Gson().toJson(m);
        mockMvc.perform(post("/api/movies/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        
	}
	@Test
	public void getTest() throws Exception{
		//Movie n = movieRepository.findByMovieName("TEST");   
		//String query = "/api/movies/";
		//query+=n.getId();
		//System.out.println(query);
		mockMvc.perform(get("/api/movies/6"))
		.andExpect(status().isOk())
		//.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.movieName").value("TEST"));
		//;
	}
	

}
