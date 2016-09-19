package at.hf.stopwatch.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.dao.ParticipantDao;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

public class ParticipantService extends EntityService<Participant> {

	@Inject
	private ParticipantDao participantDao;
	private List<Participant> filtered;

	@Override
	protected EntityDao<Participant> getDao() {
		return participantDao;
	}

	public List<Participant> filterParticipantsWithNumber(Competition competition) {
		filtered = new ArrayList<Participant>();
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

	public List<Participant> findStartersForStartBlock(Competition competition, Integer startBlock) {
		return participantDao.findStartersForBlock(competition, startBlock);
	}

	public List<Participant> findAllResults(Competition competition) {
		return participantDao.findAllResults(competition);
	}

	public List<Participant> findResultsForClassification(Competition competition, Classification classification) {
		return participantDao.findResultsForClassification(competition, classification);
	}

}
