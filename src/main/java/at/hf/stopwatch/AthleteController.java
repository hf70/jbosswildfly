package at.hf.stopwatch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.service.AthleteService;

@Controller
public class AthleteController implements Serializable {
	@Inject
	FacesContext facesContext;

	@Inject
	AthleteService athleteService;
	
	@Inject
	Event <AthleteListModifiedEvent> athleteListModifiedEvent;

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
		String summary = athlete.composeSummary();
		athleteService.delete(athlete);
		
		facesContext.addMessage(null, new FacesMessage(summary + " wurde gelöscht"));	
		athleteListModifiedEvent.fire(new AthleteListModifiedEvent());
	}
	
	public boolean isParticipant(Athlete athlete){
		return athleteService.isParticipant(athlete.getId());
	}

	public List<Athlete> getAthletes() {
		return athletes;
	}
	
	public void onAthleteListModified(@Observes AthleteListModifiedEvent athleteListModifiedEvent){
		loadAthletes();
	}

}
