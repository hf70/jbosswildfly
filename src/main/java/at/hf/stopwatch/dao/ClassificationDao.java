package at.hf.stopwatch.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.FemaleClassification;
import at.hf.stopwatch.model.MaleClassification;
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
		query.setParameter(Classification.PARAM_COMPETITION, participant.getCompetition());
		query.setParameter(Classification.PARAM_TYPE, determineClassificationClass(participant));
		return query.getResultList();

	}

	private Class<?> determineClassificationClass(Participant participant) {
		if (participant.getAthlete().getGender().equals("m")) {
			return MaleClassification.class;
		}
		return FemaleClassification.class;
	}

}
