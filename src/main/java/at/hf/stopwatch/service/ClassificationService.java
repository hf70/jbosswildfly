package at.hf.stopwatch.service;

import javax.inject.Inject;

import at.hf.stopwatch.dao.ClassificationDao;
import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.Classification;

public class ClassificationService extends EntityService<Classification> {

	@Inject
	private ClassificationDao classificationDao;

	@Override
	protected EntityDao<Classification> getDao() {
		return classificationDao;
	}

}
