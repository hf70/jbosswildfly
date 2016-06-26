package at.hf.stopwatch;

import static org.junit.Assert.*;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.hf.stopwatch.events.AthleteListModifiedEvent;
import at.hf.stopwatch.service.AthleteService;

@RunWith(MockitoJUnitRunner.class)
public class NewAthleteDialogControllerTest {

	@Mock
	AthleteService athleteService;
	
	@Mock
	Event<AthleteListModifiedEvent> athleteListModifiedEvent;


	@InjectMocks
	NewAthleteDialogController newAthleteDialogController;

	@Test
	public void newAthleteIsNotNullAfterInit() {
		newAthleteDialogController.init();
		assertTrue(newAthleteDialogController.getNewAthlete() != null);
	}

	@Test
	public void onSaveCheckForExistingIsCalled(){
		newAthleteDialogController.init();
		newAthleteDialogController.saveNewAthlete();
		Mockito.verify(athleteService).containsExisting(newAthleteDialogController.getNewAthlete());
	}
	
}