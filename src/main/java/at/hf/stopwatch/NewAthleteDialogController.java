package at.hf.stopwatch;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import at.hf.stopwatch.cdi.Controller;
import at.hf.stopwatch.events.AthleteListModifiedEvent;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.service.AthleteService;

@Controller
public class NewAthleteDialogController implements Serializable {
	@Inject
	AthleteService athleteService;
	@Inject
	Event<AthleteListModifiedEvent> athleteListModifiedEvent;
	@Inject
	FacesContext facesContext;

	private Athlete newAthlete;
	private boolean saved;
	private boolean returnToNewParticipantDialog;

	@PostConstruct
	public void init() {
		setSaved(false);
		newAthlete = new Athlete();
	}

	private boolean athleteIsUnique() {
		if (athleteService.containsExisting(newAthlete)) {
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Der Speichervorgang war nicht erfolgreich",
					"Es gibt bereits einen Eintrag mit diesen Werten."));
			facesContext.validationFailed();
			return false;
		}
		return true;
	}

	@Transactional
	public void saveNewAthlete() {
		if (!athleteIsUnique()) {
			return;
		}
		athleteService.save(newAthlete);
		setSaved(true);
		athleteListModifiedEvent.fire(new AthleteListModifiedEvent());

	}

	public Athlete getNewAthlete() {
		return newAthlete;
	}

	public void reset() {
		System.out.println("reset");
		newAthlete = new Athlete();
		setSaved(false);
		setReturnToNewParticipantDialog(false);
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public boolean isReturnToNewParticipantDialog() {
		return returnToNewParticipantDialog;
	}
	public void setReturnToNewParticipantDialog(){
		this.returnToNewParticipantDialog=true;
	}

	public void setReturnToNewParticipantDialog(boolean returnToNewParticipantDialog) {
		this.returnToNewParticipantDialog = returnToNewParticipantDialog;
	}

}
