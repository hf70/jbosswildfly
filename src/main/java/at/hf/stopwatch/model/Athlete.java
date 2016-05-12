package at.hf.stopwatch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "athlete", uniqueConstraints = @UniqueConstraint(columnNames = {
		"firstname", "lastname", "gender", "club" }))
@NamedQuery(name = Athlete.FIND_EXISTING, query = "SELECT a FROM Athlete a  WHERE a.firstName = :"
		+ Athlete.PARAM_FIRST_NAME
		+ " AND a.lastName= :"
		+ Athlete.PARAM_LAST_NAME
		+ " AND a.yearOfBirth= :"
		+ Athlete.PARAM_YEAR_OF_BIRTH
		+ " AND a.gender= :"
		+ Athlete.PARAM_GENDER + " AND a.club = :" + Athlete.PARAM_CLUB)
public class Athlete extends BaseEntity {
	public static final String FIND_EXISTING = "Athlete.findExisting";
	public static final String PARAM_FIRST_NAME = "firstname";
	public static final String PARAM_LAST_NAME = "lastname";
	public static final String PARAM_YEAR_OF_BIRTH = "yearofbirth";
	public static final String PARAM_GENDER = "gender";
	public static final String PARAM_CLUB = "club";

	private String gender;
	private String firstName;
	private String lastName;
	private String club;
	private int yearOfBirth;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "athlete", cascade = CascadeType.ALL)
	private List<Participant> participants = new ArrayList<Participant>();

	public List<Participant> getParticipants() {
		return participants;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	@Transient
	public String composeSummary() {
		StringBuffer summary = new StringBuffer();
		summary.append(firstName + " ");
		summary.append(lastName + " ");
		summary.append(yearOfBirth + " ");
		if (StringUtils.isNotBlank(club)) {
			summary.append(club);
		}
		return summary.toString();
	}

}
