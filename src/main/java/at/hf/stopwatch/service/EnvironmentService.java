package at.hf.stopwatch.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import at.hf.stopwatch.cdi.Service;
import at.hf.stopwatch.dao.AthleteDao;
import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.Athlete;
import at.hf.stopwatch.model.Participant;

@Service
public class EnvironmentService implements Serializable {

	public boolean isTestEnvironment(){
		return true;
	}
	   

}
