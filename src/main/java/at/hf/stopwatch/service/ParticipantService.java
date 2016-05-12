package at.hf.stopwatch.service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.dao.ParticipantDao;
import at.hf.stopwatch.model.Participant;

public class ParticipantService extends EntityService<Participant> {

	 @Inject
	    private ParticipantDao participantDao;

	   
	@Override
	protected EntityDao<Participant> getDao() {
		return participantDao;
	}
	
		

}
