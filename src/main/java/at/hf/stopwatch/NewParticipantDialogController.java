package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.AthleteService;
import at.hf.stopwatch.service.CompetitionService;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class NewParticipantDialogController implements Serializable {

	private Participant newParticipant;

	@Inject
	private AthleteService athleteService;

	@Inject
	private ParticipantService participantService;

	private List<Athlete> athletes;
	private Competition competiton;
	
	@PostConstruct
	public void init() {
			athletes = athleteService.findAll();
	}
	
	public void beforeOpen(){
		System.out.println("beforeOpen");
		init();
		newParticipant = new Participant();
		assignCompetition();
	}

	public Participant getNewParticipant() {
		return newParticipant;
	}

	public void assignCompetition() {
		newParticipant.setCompetition(competiton);
	}

	

	public List<Athlete> getAthletes() {
		return athletes;
	}

	public List<Athlete> completeAthlete(String query) {
		List<Athlete> filteredAthletes = new ArrayList<Athlete>();

		for (Athlete athlete : athletes) {
			if (athleteMatchesQuery(athlete, query)) {
				filteredAthletes.add(athlete);
			}
		}

		return filteredAthletes;
	}

	private boolean athleteMatchesQuery(Athlete athlete, String query) {
		String matchString = athlete.getLastName().concat(" ")
				.concat(athlete.getFirstName());
		return matchString.contains(query);
	}

	@Transactional
	public void saveNewParticipant() {
		participantService.save(newParticipant);
	
	}

	public Competition getCompetiton() {
		return competiton;
	}

	public void setCompetiton(Competition competiton) {
		this.competiton = competiton;
	}

}
