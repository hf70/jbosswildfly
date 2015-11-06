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
public class NewAthleteDialogController implements Serializable {
	@Inject
	AthleteService athleteService;

	private Athlete newAthlete;
 
	private Map<String, String> genderList;
	
	@PostConstruct
	public void init() {
		newAthlete = new Athlete();
		genderList = new HashMap<String, String>();
	        genderList.put("Mann","m");
	        genderList.put("Frau","w");
	}

	@Transactional
	public void saveNewAthlete() {
		athleteService.save(newAthlete);
		init();
	}


	public Athlete getNewAthlete() {
		return newAthlete;
	}

	public Map<String, String> getGenderList() {
		return genderList;
	}


}
