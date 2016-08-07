package at.hf.stopwatch.service;

import java.util.List;

import javax.inject.Inject;

import at.hf.stopwatch.dao.ClassificationDao;
import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Classification;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

public class ClassificationService extends EntityService<Classification> {

	@Inject
	private ClassificationDao classificationDao;

	@Override
	protected EntityDao<Classification> getDao() {
		return classificationDao;
	}
	
	public List<Classification> getSelectableForParticipant(Participant participant){
		return classificationDao.findSelectableForParticipant(participant);
	}

}
