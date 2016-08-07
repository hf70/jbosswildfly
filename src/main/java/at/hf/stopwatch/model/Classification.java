package at.hf.stopwatch.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "classification")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "gender")
@NamedQuery(name = Classification.FIND_SELECTABLE_FOR_PARTICIPANT, query = "SELECT c FROM Classification c Where c.fromYearOfBirth >= :"
		+ Classification.PARAM_YEAR_OF_BIRTH + " and   c.toYearOfBirth <= :" + Classification.PARAM_YEAR_OF_BIRTH)

public class Classification extends BaseEntity {
	public static final String FIND_SELECTABLE_FOR_PARTICIPANT = "Classification.findSelectableForParticipant";
	public static final String PARAM_YEAR_OF_BIRTH = "yearofbirth";

	private int fromYearOfBirth;
	private int toYearOfBirth;
	private String shortName;
	private String longName;
	@ManyToOne()
	@JoinColumn(name = "competition_id")
	private Competition competition;

	public int getFromYearOfBirth() {
		return fromYearOfBirth;
	}

	public void setFromYearOfBirth(int fromYearOfBirth) {
		this.fromYearOfBirth = fromYearOfBirth;
	}

	public int getToYearOfBirth() {
		return toYearOfBirth;
	}

	public void setToYearOfBirth(int toYearOfBirth) {
		this.toYearOfBirth = toYearOfBirth;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

}
