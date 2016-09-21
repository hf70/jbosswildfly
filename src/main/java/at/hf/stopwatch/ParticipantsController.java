package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.events.ParticipantListModifiedEvent;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.ClassificationService;
import at.hf.stopwatch.service.CompetitionService;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class ParticipantsController implements Serializable {

	@Inject
	ParticipantService participantService;
	@Inject
	FacesContext facesContext;
	@Inject
	CompetitionService competitionService;
	@Inject
	ClassificationService classificationService;
	@Inject
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;

	private Competition competition;
	private List<Participant> participants;
	private List<Participant> filteredParticipants;
	private String filter;

	@PostConstruct
	public void setup(){
		participants= new ArrayList<Participant>();
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public List<Classification> classificationsForParticipant(Participant participant) {
		return classificationService.getSelectableForParticipant(participant);
	}
	
	
	
	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	@Transactional
	public void removeParticipant(Participant participant) {
		competition.getParticipants().remove(participant);
		participantService.delete(participant);
		competitionService.save(competition);
		
		filteredParticipants=null;

		participantListModifiedEvent.fire(new ParticipantListModifiedEvent());
		facesContext.addMessage(null, new FacesMessage("Der Teilnehmer wurde gelöscht"));
	
	}
	
	@Transactional
	public void onCellEdit(Participant participant) {		
		participantService.save(participant);
		participantListModifiedEvent.fire(new ParticipantListModifiedEvent());
		facesContext.addMessage(null, new FacesMessage("Änderung für Teilnehmer " + participant.getAthlete().getLastName() + " wurde gespeichert."));
	}
	
	public void resetFilter(){
		this.filter= null;
	}

	public List<Participant> getFilteredParticipants() {
		return filteredParticipants;
	}

	public void setFilteredParticipants(List<Participant> filteredParticipants) {
		this.filteredParticipants = filteredParticipants;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
