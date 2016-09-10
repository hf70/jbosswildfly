package at.hf.stopwatch.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.dao.ParticipantDao;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

public class ParticipantService extends EntityService<Participant> {

	@Inject
	private ParticipantDao participantDao;

	@Override
	protected EntityDao<Participant> getDao() {
		return participantDao;
	}

	public List<Participant> filterParticipantsWithNumber(Competition competition) {
		List<Participant> filtered = new ArrayList<Participant>();
		if (competition == null) {
			return filtered;
		}

		for (Participant participant : competition.getParticipants()) {
			if (participant.getNumber() > 0) {
				filtered.add(participant);
			}
		}
		return filtered;
	}

}
