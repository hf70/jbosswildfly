package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.events.ParticipantListModifiedEvent;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.CompetitionService;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class CompetitionController implements Serializable {

	@Inject
	FacesContext facesContext;
	@Inject
	CompetitionService competitionService;
	@Inject
	NewParticipantDialogController newParticipantDialogController;
	@Inject
	ParticipantService participantService;
	@Inject
	ParticipantsController participantsController;
	@Inject
	RuntimesController runtimesController;
	@Inject
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;
	@Inject
	ClassificationController classificationController;

	private int id;
	private Competition competition;

	public void loadCompetition() {		
		competition = competitionService.findById(id);
		newParticipantDialogController.setCompetiton(competition);
		participantsController.setCompetition(competition);
		runtimesController.setCompetition(competition);
		classificationController.setCompetition(competition);
		participantsController.setParticipants(convertParticipants());
	}


	public Competition getCompetition() {
		return competition;
	}

	@Transactional
	public void saveCompetition() {
		competitionService.save(competition);
	}

	public void onParticipantListModified(@Observes ParticipantListModifiedEvent participantListModifiedEvent) {
		loadCompetition();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private List<Participant> convertParticipants() {
		List<Participant> participants = new ArrayList<Participant>();
		participants.addAll(competition.getParticipants());
		return participants;
	}



}
