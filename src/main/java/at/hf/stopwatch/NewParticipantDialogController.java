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
	private int competitionId;

	@Inject
	CompetitionService competitionService;

	@Inject
	AthleteService athleteService;

	@Inject
	ParticipantService participantService;

	List<Athlete> athletes;

	@PostConstruct
	public void init() {
		newParticipant = new Participant();
		athletes = athleteService.findAll();

	}

	public Participant getNewParticipant() {
		return newParticipant;
	}

	public void assignCompetition() {
		newParticipant.setCompetition(competitionService
				.findById(competitionId));
	}

	public int getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
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
		init();
	}

}
