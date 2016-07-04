package at.hf.stopwatch.dao;

import java.util.List;
import java.util.ListIterator;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Competition;

public class AthleteDao extends EntityDao<Athlete> {

	@Override
	protected Class<Athlete> getResponseClass() {
		return Athlete.class;
	}

	public List<Athlete> findExisting(Athlete athlete) {

		TypedQuery<Athlete> query = createNamedQuery(Athlete.FIND_EXISTING, Athlete.class);
		query.setParameter(Athlete.PARAM_FIRST_NAME, athlete.getFirstName());
		query.setParameter(Athlete.PARAM_LAST_NAME, athlete.getLastName());
		query.setParameter(Athlete.PARAM_GENDER, athlete.getGender());
		query.setParameter(Athlete.PARAM_YEAR_OF_BIRTH, athlete.getYearOfBirth());
		query.setParameter(Athlete.PARAM_CLUB, athlete.getClub());
		return query.getResultList();
	}

	public List<Athlete> findSelectableForCompetition(Competition competition) {
		TypedQuery<Athlete> query = createNamedQuery(Athlete.FIND_SELECTABLE_FOR_COMPETITION, Athlete.class);
		query.setParameter(Athlete.PARAM_COMPETITION_ID, competition.getId());
		return query.getResultList();

	}

}
