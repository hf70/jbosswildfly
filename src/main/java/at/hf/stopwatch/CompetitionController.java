package at.hf.stopwatch;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.dao.CompetitionDao;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.service.CompetitionService;

@Controller
public class CompetitionController implements Serializable {
	@Inject
	CompetitionDao competitionDao;

	@Inject
	CompetitionService competitionService;

	private List<Competition> competitions;

	private Competition newCompetition;

	public Competition getNewCompetition() {
		return newCompetition;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	@PostConstruct
	public void init() {
		loadCompetitions();
		newCompetition = new Competition();
	}

	public void loadCompetitions() {
		competitions = competitionService.findAll();
	}

	@Transactional
	public void saveNewCompetition() {
		competitionService.save(newCompetition);
		init();
	}
	 @Transactional
	    public void removeCompetiton(Competition competition) {
		 competitionService.delete(competition);
		 loadCompetitions();
	    }

}
