package at.hf.stopwatch.cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EntityManagerProducer {

	@Produces
	@PersistenceContext
	transient EntityManager entityManager;
}
