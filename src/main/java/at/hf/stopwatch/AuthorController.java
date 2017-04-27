package at.hf.stopwatch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.events.AthleteListModifiedEvent;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Author;
import at.hf.stopwatch.service.AthleteService;
import at.hf.stopwatch.service.AuthorService;

@Controller
public class AuthorController implements Serializable {

	@Inject
	AuthorService authorService;


	private List<Author> authors;

	@PostConstruct
	public void init() {
		loadAuthors();
	}

	public void loadAuthors() {
		authors= authorService.query();
	}

	public List<Author> getAuthors() {
		return authors;
	}

		

}
