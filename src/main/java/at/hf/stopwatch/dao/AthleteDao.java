package at.hf.stopwatch.dao;

import at.hf.stopwatch.model.Athlete;

public class AthleteDao extends EntityDao {

	@Override
	protected Class getResponseClass() {
		return Athlete.class;
	}

}
