package at.hf.stopwatch.dao;

import java.util.ArrayList;
import java.util.List;

import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.FemaleClassification;

public class CompetitionDao extends EntityDao<Competition> {

	@Override
	protected Class<Competition> getResponseClass() {
		return Competition.class;
	}

	public List<FemaleClassification> findAllFemaleClassifications(){
		List<FemaleClassification> femaleClassifications = new ArrayList <FemaleClassification>();
		return femaleClassifications;
	}
}
