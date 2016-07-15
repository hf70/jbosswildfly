package at.hf.stopwatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
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

	@Test
	public void onRemoveParticipantParticipantModifiedListEventIsFired() {
		competitionController.removeParticipant(participant);
		Mockito.verify(participantListModifiedEvent).fire(Mockito.any(ParticipantListModifiedEvent.class));
	}

	@Test
	public void onRemoveParticipantServiceIsCalled() {
		competitionController.removeParticipant(participant);
		Mockito.verify(participantService).delete(participant);
	}

	@Test
	public void onRemoveParticipantCompetitionIsSave() {
		competitionController.removeParticipant(participant);
		Mockito.verify(competitionService).save(competition);

	}

	private void prepareCompetition() {
		Set<Participant> participants = new HashSet<Participant>();
		participants.add(participant);
		competition.setParticipants(participants);

	}

}
