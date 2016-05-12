package at.hf.stopwatch.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;



@Entity
@Table(name = "competition")
public class Competition extends BaseEntity {

	public static final String FIND_All = "Competiton.findAll";
	private Date date;


	private String subject;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval=true,mappedBy = "competition", cascade = ALL)
	private List<Participant> participants = new ArrayList<Participant>();
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Competition() {
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}


}