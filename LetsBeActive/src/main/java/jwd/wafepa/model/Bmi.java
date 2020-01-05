package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_bmi")
public class Bmi {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	

	
	
	
	@Column(name="bmi")
	private String bmi;
	
	@Column(name="date")
	private String date;
	
	@Column(name="note")
	private String note;
	
	public Bmi() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getBmi() {
		return bmi;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
