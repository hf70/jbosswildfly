package at.hf.stopwatch.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "competition")
public class Competition extends BaseEntity {

	public static final String FIND_All = "Competiton.findAll";
	private Date date;

	private String subject;
	
	@OneToMany(mappedBy="competition", cascade = CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval = true) 
	private List<Participant> participants ;
	
	@OneToMany(mappedBy="competition", cascade = CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval = true) 
	private List<Classification> classifications;
	
	
	public Competition() {
	}
	
	public List<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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