package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

	@Inject
	private AthleteService athleteService;
	@Inject
	private ParticipantService participantService;
	@Inject
	private ParticipantsController participantsController;
	@Inject
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;

	private Participant newParticipant;
	private List<Athlete> athletes;
	private List<Athlete> filteredAthletes;
	private Competition competiton;

	public void beforeOpen() {
		System.out.println("beforeOpen");
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

	public List<Athlete> getFilteredAthletes() {
		return filteredAthletes;
	}

	public void setFilteredAthletes(List<Athlete> filteredAthletes) {
		this.filteredAthletes = filteredAthletes;
	}

	public List<Athlete> completeAthlete(String query) {
		filteredAthletes = new ArrayList<Athlete>();

		for (Athlete athlete : athletes) {
			if (athleteMatchesQuery(athlete, query)) {
				filteredAthletes.add(athlete);
			}
		}

		return filteredAthletes;
	}

	private boolean athleteMatchesQuery(Athlete athlete, String query) {
		String matchString = athlete.getLastName().concat(" ").concat(athlete.getFirstName());
		return matchString.contains(query);
	}

	@Transactional
	public void saveNewParticipant() {
		participantService.save(newParticipant);
		filterParticipantsByNewParticipant();
		participantListModifiedEvent.fire(new ParticipantListModifiedEvent());
	}

	private void filterParticipantsByNewParticipant() {
		participantsController.setFilter(newParticipant.getAthlete().getLastName());

	}

	public Competition getCompetiton() {
		return competiton;
	}

	public void setCompetiton(Competition competiton) {
		this.competiton = competiton;
	}

}
