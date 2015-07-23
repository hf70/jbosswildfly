package at.hf.stopwatch.service;

import java.util.List;

import javax.inject.Inject;

import at.hf.stopwatch.dao.CompetitionDao;
import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.Competition;

public class CompetitionService extends EntityService<Competition> {

	 @Inject
	    private CompetitionDao competitionDao;

	    @Override
	    protected EntityDao<Competition> getDao() {
	        return competitionDao;
	    }
	    
	    public List<Competition> findAll(){
	    	return competitionDao.findAll();
	    }

}
