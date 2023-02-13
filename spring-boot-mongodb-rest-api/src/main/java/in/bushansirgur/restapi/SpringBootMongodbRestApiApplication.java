package in.bushansirgur.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
@Controller
public class SpringBootMongodbRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbRestApiApplication.class, args);
	}

	@GetMapping("/")
	public String welcome() {
		return "/HTML/index.html";
	}

	@GetMapping("/create-note.html")
	public String createNote() {
		return "/HTML/create-note.html";
	}

	@GetMapping("/see-notes.html")
	public String seeNotes() {
		return "/HTML/see-notes.html";
	}

}

