package at.hf.stopwatch;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.hf.stopwatch.events.ParticipantListModifiedEvent;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.service.AthleteService;
import at.hf.stopwatch.service.ParticipantService;

@RunWith(MockitoJUnitRunner.class)
public class NewParticipantDialogControllerTest {
	@Mock
	private AthleteService athleteService;
	@Mock
	private ParticipantService participantService;
	@Mock
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;

	@InjectMocks
	NewParticipantDialogController newParticipantDialogController;

	List<Athlete> athletes = new ArrayList<Athlete>();

	@Before
	public void setup() {
		Mockito.when(athleteService.findAll()).thenReturn(athletes);
		newParticipantDialogController.init();
	}

	@Test
	public void onSaveParticipantModifiedListEventIsFired() {

		newParticipantDialogController.saveNewParticipant();
		Mockito.verify(participantListModifiedEvent).fire(Mockito.any(ParticipantListModifiedEvent.class));
	}

}
