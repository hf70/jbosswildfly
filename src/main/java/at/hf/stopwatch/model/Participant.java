package at.hf.stopwatch.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participants")
public class Participant extends BaseEntity {

	private int number;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "athleteid", unique = true, nullable = false, updatable = false)
	private Athlete athlete;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionid", nullable = false, insertable = false, updatable = false)

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
