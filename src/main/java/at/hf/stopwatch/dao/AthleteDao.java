package at.hf.stopwatch.dao;

import java.util.List;
import java.util.ListIterator;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import at.hf.stopwatch.model.Athlete;

public class AthleteDao extends EntityDao<Athlete> {

	@Override
	protected Class<Athlete> getResponseClass() {
		return Athlete.class;
	}

	public List<Athlete> findExisting(Athlete athlete) {
		
		System.out.println("findExisting=" + athlete.composeSummary());
		TypedQuery<Athlete> query = createNamedQuery(Athlete.FIND_EXISTING,
				Athlete.class);
		query.setParameter(Athlete.PARAM_FIRST_NAME, athlete.getFirstName());
		query.setParameter(Athlete.PARAM_LAST_NAME, athlete.getLastName());
		query.setParameter(Athlete.PARAM_GENDER, athlete.getGender());
		query.setParameter(Athlete.PARAM_YEAR_OF_BIRTH,
				athlete.getYearOfBirth());
		query.setParameter(Athlete.PARAM_CLUB, athlete.getClub());
		List<Athlete> result=query.getResultList();
		ListIterator<Athlete> it = result.listIterator();
		while(it.hasNext()){
			System.out.println(it.next().composeSummary());
		}
		return result;
	}

}
