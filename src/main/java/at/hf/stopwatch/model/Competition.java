package at.hf.stopwatch.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the competitions database table.
 * 
 */

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {

	public static final String FIND_All = "Competiton.findAll";
	private Date date;


	private String subject;
	
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

	

}