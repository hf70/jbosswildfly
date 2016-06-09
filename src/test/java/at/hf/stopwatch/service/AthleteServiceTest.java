package at.hf.stopwatch.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.hf.stopwatch.model.Competition;

@RunWith(MockitoJUnitRunner.class)
public class AthleteServiceTest {
	
	@Mock
	Competition competition;

	@InjectMocks
	AthleteService athleteService;
	
	
	@Before
	public void setup(){
		Mockito.when(competition.getId()).thenReturn(1);
	}
	
	@Test
	public void athletesThatAreAllreadyParticipantForTheCompetitonAreNotReturnedInList(){
		
	}
	
}
