package at.hf.stopwatch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant extends BaseEntity {

	private int number;
	private Long runtime;
	private int startblock;

	@ManyToOne
	private Athlete athlete;

	@ManyToOne
	private Competition competition;
	
	@ManyToOne
	private Classification classification;

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

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public Long getRuntime() {
		return runtime;
	}

	public void setRuntime(Long runtime) {
		this.runtime = runtime;
	}

	public int getStartblock() {
		return startblock;
	}

	public void setStartblock(int startblock) {
		this.startblock = startblock;
	}

	

	

}
