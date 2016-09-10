package at.hf.stopwatch;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.hf.stopwatch.events.ParticipantListModifiedEvent;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;
import at.hf.stopwatch.service.CompetitionService;
import at.hf.stopwatch.service.ParticipantService;

@RunWith(MockitoJUnitRunner.class)
public class CompetitionControllerTest {
	private static final int COMPETITION_ID = 0;
	@Mock
	CompetitionService competitionService;
	@Mock
	ParticipantService participantService;
	@Mock
	NewParticipantDialogController newParticipantDialogController;
	@Mock
	Event<ParticipantListModifiedEvent> participantListModifiedEvent;
	@Mock
	FacesContext facesContext;
	@Mock
	@Inject
	ClassificationController classificationController;

	@InjectMocks
	CompetitionController competitionController;

	Competition competition = new Competition();
	Participant participant = new Participant();

	@Before
	public void setup() {

		prepareCompetition();

		Mockito.when(competitionService.findById(Mockito.anyInt())).thenReturn(competition);
		Mockito.doNothing().when(participantService).delete(Mockito.any(Participant.class));

		competitionController.setId(COMPETITION_ID);
		competitionController.loadCompetition();
	}


	

	private void prepareCompetition() {
		Set<Participant> participants = new HashSet<Participant>();
		participants.add(participant);
		competition.setParticipants(participants);

	}

}
