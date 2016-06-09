package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.events.AthleteListModifiedEvent;
import at.hf.stopwatch.events.ParticipantListModifiedEvent;
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
	
	@Inject
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;

	private List<Athlete> athletes;
	private Competition competiton;
	
	@PostConstruct
	public void init() {
			
	}
	
	public void beforeOpen(){
		init();
		newParticipant = new Participant();
		assignCompetition();
		athletes = athleteService.getSelectableForCompetition(competiton);
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
		participantListModifiedEvent.fire(new ParticipantListModifiedEvent());
	
	}

	public Competition getCompetiton() {
		return competiton;
	}

	public void setCompetiton(Competition competiton) {
		this.competiton = competiton;
	}

}
