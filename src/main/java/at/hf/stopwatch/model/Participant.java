package at.hf.stopwatch.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant extends BaseEntity {

	private int number;

	@OneToOne()
	@JoinColumn(name = "athlete_id",nullable=false)
	private Athlete athlete;

	@ManyToOne()
	@JoinColumn(name = "competition_id")
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
