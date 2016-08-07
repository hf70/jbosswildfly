package at.hf.stopwatch.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

public class ClassificationDao extends EntityDao<Classification> {

	@Override
	protected Class<Classification> getResponseClass() {
		return Classification.class;
	}

	public List<Classification> findSelectableForParticipant(Participant participant) {
		TypedQuery<Classification> query = createNamedQuery(Classification.FIND_SELECTABLE_FOR_PARTICIPANT,
				Classification.class);
		query.setParameter(Classification.PARAM_YEAR_OF_BIRTH, participant.getAthlete().getYearOfBirth());
		return query.getResultList();

	}

}
