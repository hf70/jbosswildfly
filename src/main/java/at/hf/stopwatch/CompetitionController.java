package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
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
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;
	@Inject
	ClassificationController classificationController;

	private int id;
	private Competition competition;
	private List<Participant> participants;

	public void loadCompetition() {
		competition = competitionService.findById(id);
		newParticipantDialogController.setCompetiton(competition);
		classificationController.setCompetition(competition);
		participants= new ArrayList<Participant>();
		participants.addAll(competition.getParticipants());
	}

	public Competition getCompetition() {
		return competition;
	}

	@Transactional
	public void saveCompetition() {
		competitionService.save(competition);
	}

	@Transactional
	public void removeParticipant(Participant participant) {
		competition.getParticipants().remove(participant);
		participantService.delete(participant);

		competitionService.save(competition);

		facesContext.addMessage(null, new FacesMessage("Der Teilnehmer wurde gelöscht"));
		participantListModifiedEvent.fire(new ParticipantListModifiedEvent());

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

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

}
