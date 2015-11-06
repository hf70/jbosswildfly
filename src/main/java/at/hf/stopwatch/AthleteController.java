package at.hf.stopwatch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.service.AthleteService;

@Controller
public class AthleteController implements Serializable {
	@Inject
	AthleteService athleteService;

	private List<Athlete> athletes;

	@PostConstruct
	public void init() {
		loadAthletes();
	}

	public void loadAthletes() {
		athletes = athleteService.findAll();
	}

	@Transactional
	public void removeAthlete(Athlete athlete) {
		athleteService.delete(athlete);
		loadAthletes();
	}

	public List<Athlete> getAthletes() {
		return athletes;
	}

}
