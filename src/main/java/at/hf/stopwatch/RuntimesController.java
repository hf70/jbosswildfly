package at.hf.stopwatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.primefaces.event.SelectEvent;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.ParticipantService;

@Controller
public class RuntimesController implements Serializable {

	@Inject
	ParticipantService participantService;

	private Competition competition;
	
	private Participant newRuntimeEntry;
	
	private String newRuntime;

	
	public  List<Participant> autocompleteStartnummer(String input){
		
		List<Participant> suggestions= new ArrayList<Participant>();
		for (Participant participant:participantService.filterParticipantsWithNumber(competition)){
			if (input.isEmpty() || String.valueOf(participant.getNumber()).startsWith(input)){
				suggestions.add(participant);
			}
		}
		return suggestions;
	}
	
	public void participantSelected(SelectEvent event){
		
		newRuntimeEntry = (Participant) event.getObject();
	}
	
	@Transactional
	public void saveRuntime(){
		participantService.save(newRuntimeEntry);
		newRuntimeEntry = null;
	}
	
	public List<Participant> getParticipants() {
		return participantService.filterParticipantsWithNumber(competition);
	}

	public Participant getNewRuntimeEntry() {
		return newRuntimeEntry;
	}

	public void setNewRuntimeEntry(Participant newRuntimeEntry) {
		this.newRuntimeEntry = newRuntimeEntry;
	}

	public String getNewRuntime() {
		return newRuntime;
	}

	public void setNewRuntime(String newRuntime) {
		this.newRuntime = newRuntime;
	}
	
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}


	

}
