package jwd.wafepa.web.dto;

public class BmiDTO {

  private Long id;
	
	private String bmi;
	
	private String note;

	private String date;


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
