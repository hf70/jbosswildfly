package at.hf.stopwatch.cdi;

import java.time.Clock;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

public class ClockProducer {

	@Produces
	Clock getClock() {
		return Clock.systemDefaultZone();
	}

}
