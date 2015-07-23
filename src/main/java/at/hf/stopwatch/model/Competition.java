package at.hf.stopwatch.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the competitions database table.
 * 
 */

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {

	public static final String FIND_All = "Competiton.findAll";
	@Temporal(TemporalType.DATE)
	private Date date;

	@Lob
	private String name;

	public Competition() {
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}