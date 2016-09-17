package at.hf.stopwatch.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

public class ParticipantDao extends EntityDao<Participant> {

	@Override
	protected Class<Participant> getResponseClass() {
		return Participant.class;
	}

	
	public List<Participant> findStartersForBlock(Competition competition, Integer startBlock) {

		TypedQuery<Participant> query = createNamedQuery(Participant.FIND_STARTERS, Participant.class);
		query.setParameter(Participant.PARAM_COMPETITION, competition);
		query.setParameter(Participant.PARAM_START_BLOCK, startBlock);		
		return query.getResultList();
	}
	
}
