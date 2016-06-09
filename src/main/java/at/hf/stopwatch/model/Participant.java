package at.hf.stopwatch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant extends BaseEntity {

	private int number;

	@ManyToOne
	private Athlete athlete;

	@ManyToOne
	private Competition competition;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Athlete getAthlete() {
		return this.athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

}
