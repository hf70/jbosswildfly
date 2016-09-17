package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
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
	@Inject
	StartListController startListController;

	private int id;
	private Competition competition;
	private Map<String, Integer> startBlockList = new TreeMap<String,Integer> ();
 	
	

	public Map<String, Integer> getStartBlockList() {
		return startBlockList;
	}

	public void loadCompetition() {		
		competition = competitionService.findById(id);
		newParticipantDialogController.setCompetiton(competition);
		participantsController.setCompetition(competition);
		participantsController.setParticipants(convertParticipants());
		runtimesController.setCompetition(competition);
		classificationController.setCompetition(competition);
		startListController.setCompetition(competition);
		updateStartBlockList();

		
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

	private void updateStartBlockList() {
		startBlockList.clear();

		for (int i = 1; i <= competition.getStartBlocks(); i++) {
			startBlockList.put("Block " + String.valueOf(i), i);
		}
	}

	

}
