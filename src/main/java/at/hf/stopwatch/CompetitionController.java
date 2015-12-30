package at.hf.stopwatch;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.service.CompetitionService;

@Controller
public class CompetitionController implements Serializable {

	@Inject
	CompetitionService competitionService;
	@Inject
	NewParticipantDialogController newParticipantDialogController;
	
	
	private int id;
	private Competition competition;
	
	public void loadCompetition(){
		competition=competitionService.findById(id);
		
		newParticipantDialogController.setCompetitionId(id);
		newParticipantDialogController.assignCompetition();
	}

	public Competition getCompetition() {
		return competition;
	}
	
	@Transactional
	public void saveCompetition(){
		competitionService.save(competition);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
