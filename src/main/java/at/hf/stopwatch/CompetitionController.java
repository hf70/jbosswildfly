package at.hf.stopwatch;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.events.AthleteListModifiedEvent;
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

	private int id;
	private Competition competition;
	

	public void loadCompetition() {
		System.out.println("load Competition");
		competition = competitionService.findById(id);

		newParticipantDialogController.setCompetiton(competition);
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
		//competitionService.save(competition);
		loadCompetition();
		facesContext.addMessage(null, new FacesMessage(
				"Der Teilnehmer wurde gelöscht"));
	}
	
	public void onParticipantListModified(@Observes ParticipantListModifiedEvent participantListModifiedEvent){
		loadCompetition();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
