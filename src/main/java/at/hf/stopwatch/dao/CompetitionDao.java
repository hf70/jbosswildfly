package at.hf.stopwatch.dao;

import at.hf.stopwatch.model.Competition;

public class CompetitionDao extends EntityDao<Competition> {

	@Override
	protected Class<Competition> getResponseClass() {
		return Competition.class;
	}


}
