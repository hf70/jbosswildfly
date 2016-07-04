package at.hf.stopwatch.dao;

import at.hf.stopwatch.model.Classification;

public class ClassificationDao extends EntityDao<Classification>{

	@Override
	protected Class<Classification> getResponseClass() {
		return Classification.class;
	}

}
