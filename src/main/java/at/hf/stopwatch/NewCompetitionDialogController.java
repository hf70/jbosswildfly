package at.hf.stopwatch;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.service.CompetitionService;

@Controller
public class NewCompetitionDialogController implements Serializable {
	@Inject
	CompetitionService competitionService;

	
	private Competition newCompetition;

	public Competition getNewCompetition() {
		return newCompetition;
	}

		@PostConstruct
	public void init() {
			newCompetition = new Competition();
	}

		@Transactional
	public void saveNewCompetition() {
		competitionService.save(newCompetition);
		init();
	}

}
