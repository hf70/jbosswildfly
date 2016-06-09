package at.hf.stopwatch.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import at.hf.stopwatch.dao.AthleteDao;
import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Competition;
import at.hf.stopwatch.model.Participant;

public class AthleteService extends EntityService<Athlete> {

	@Inject
	private AthleteDao athleteDao;

	@Override
	protected EntityDao<Athlete> getDao() {
		return athleteDao;
	}

	public boolean isParticipant(int id) {

		return !getParticipants(id).isEmpty();
	}

	public boolean containsExisting(Athlete athlete) {
		if (athleteDao.findExisting(athlete).isEmpty()) {
			return false;
		}
		return true;	
	}
	
	public List<Athlete> getSelectableForCompetition(Competition competition){
		return athleteDao.findSelectableForCompetition(competition);
	}

	private List<Participant> getParticipants(int id) {
		return getDao().findById(id).getParticipants();
	}
	
	

}
