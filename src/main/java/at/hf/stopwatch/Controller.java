package at.hf.stopwatch;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.hf.stopwatch.dao.CompetitionDao;
import at.hf.stopwatch.model.Competition;

@ManagedBean(name="controller")
@ViewScoped
public class Controller implements Serializable {
	@Inject
	CompetitionDao competitionDao;

	private List<Competition> competitions;

	public List<Competition> getCompetitions() {
		return competitions;
	}

	@PostConstruct
	public void init() {

	}

	public void loadCompetitions() {
		competitions = competitionDao.findAll();
	}
}
