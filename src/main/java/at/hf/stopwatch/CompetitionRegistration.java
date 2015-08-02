package at.hf.stopwatch;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import at.hf.stopwatch.model.Competition;

@Stateful
@Model
public class CompetitionRegistration {

	@Inject
	private EntityManager entityManager;

	private Competition newCompetition;

	@Produces
	@Named
	public Competition getNewCompetition() {
		return newCompetition;
	}

	public void register()  throws Exception{
		entityManager.persist(newCompetition);
		initNewCompetition();
	}

	@PostConstruct
	public void initNewCompetition() {
		newCompetition = new Competition();
	}

}
