package at.hf.stopwatch.dao;

import at.hf.stopwatch.model.Participant;

public class ParticipantDao extends EntityDao<Participant> {

	@Override
	protected Class<Participant> getResponseClass() {
		return Participant.class;
	}

}
