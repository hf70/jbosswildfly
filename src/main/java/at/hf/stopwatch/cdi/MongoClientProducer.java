package at.hf.stopwatch.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.mongodb.MongoClient;

@ApplicationScoped
public class MongoClientProducer {
	@Produces
	public MongoClient mongoClient() {
		return new MongoClient("localhost", 27017);
	}
}