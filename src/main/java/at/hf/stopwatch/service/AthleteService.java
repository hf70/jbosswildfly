package at.hf.stopwatch.service;

import java.util.List;

import javax.inject.Inject;

import at.hf.stopwatch.dao.AthleteDao;
import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Competition;

public class AthleteService extends EntityService<Athlete> {

	 @Inject
	    private AthleteDao athleteDao;

	    @Override
	    protected EntityDao<Athlete> getDao() {
	        return athleteDao;
	    }
	    
	    public List<Athlete> findAll(){
	    	return athleteDao.findAll();
	    }

}
