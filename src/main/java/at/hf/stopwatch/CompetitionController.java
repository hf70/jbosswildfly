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
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.ClassificationGroup;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.FemaleClassification;
import at.hf.stopwatch.model.MaleClassification;
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
	
	
	private int id;
	private Competition competition;

	public void loadCompetition() {
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

		competitionService.save(competition);

		facesContext.addMessage(null, new FacesMessage("Der Teilnehmer wurde gelöscht"));
		participantListModifiedEvent.fire(new ParticipantListModifiedEvent());

	}
	
	public List<ClassificationGroup> getClassificationGroups() {
		List<ClassificationGroup> groups = new ArrayList<ClassificationGroup>();
		
		groups.add(createFemaleClassificationGroup());
		groups.add(createMaleClassificationGroup());
		
		return groups;
		
	}
	
	
	private  ClassificationGroup createFemaleClassificationGroup(){
		ClassificationGroup femaleGroup = new ClassificationGroup();
		femaleGroup.setName("Klassen weiblich");
		femaleGroup.setClassifications(filterFemaleClassifications());
		return femaleGroup;
	}
	
	private  ClassificationGroup createMaleClassificationGroup(){
		ClassificationGroup femaleGroup = new ClassificationGroup();
		femaleGroup.setName("Klassen männlich");
		femaleGroup.setClassifications(filterMaleClassifications());
		return femaleGroup;
	}
	


	private List<Classification> filterFemaleClassifications() {
		 List<Classification>  femaleClassification = new ArrayList<Classification>();
		 for (Classification classification: getCompetition().getClassifications()){
			 if (classification instanceof FemaleClassification){
				 femaleClassification.add( classification);
			 }
		 }
		 return femaleClassification;
	}
	
	private List<Classification> filterMaleClassifications() {
		List<Classification>  maleClassification = new ArrayList<Classification>();
		 for (Classification classification: getCompetition().getClassifications()){
			 if (classification instanceof MaleClassification){
				 maleClassification.add((Classification) classification);
			 }
		 }
		return maleClassification;
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

}
