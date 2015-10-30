package at.hf.stopwatch.dao;

import at.hf.stopwatch.model.Participant;

public class ParticipantDao extends EntityDao {

	@Override
	protected Class getResponseClass() {
		return Participant.class;
	}

}
