package at.hf.stopwatch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "participant")

@NamedQueries({
		@NamedQuery(name = Participant.FIND_STARTERS, query = "SELECT p FROM Participant p  WHERE p.number > 0 AND p.competition = :"
				+ Participant.PARAM_COMPETITION + " AND p.startblock = :" + Participant.PARAM_START_BLOCK),
		@NamedQuery(name = Participant.FIND_ALL_RESULTS, query = "SELECT p  FROM Participant p  WHERE p.competition = :"
				+ Participant.PARAM_COMPETITION + " AND  p.number > 0 AND p.runtime > 0 order by p.runtime"),
		@NamedQuery(name = Participant.FIND_RESULTS_FOR_CLASSIFICATION, query = "SELECT p  FROM Participant p  WHERE p.competition = :"
				+ Participant.PARAM_COMPETITION + " AND p.classification = :" + Participant.PARAM_CLASSIFICATION
				+ " AND  p.number > 0 AND p.runtime > 0 order by p.runtime")

})
public class Participant extends BaseEntity {

	public static final String FIND_STARTERS = "Participant.findStarters";
	public static final String FIND_ALL_RESULTS = "Participant.findAllResults";
	public static final String FIND_RESULTS_FOR_CLASSIFICATION = "Participant.findResultsForClassification";
	public static final String PARAM_COMPETITION = "competition";
	public static final String PARAM_START_BLOCK = "startblock";
	public static final String PARAM_CLASSIFICATION = "classification";

	private int number;
	private Long runtime;
	private int startblock;
	private boolean paid;

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

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
