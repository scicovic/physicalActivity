package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Record {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String time;
	
	@Column(nullable=false)
	private Integer duration;
	
	@Column
	private String intensity;
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Activity activity;
	
	public Record() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}



	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
		if(!activity.getRecords().contains(this)){
			activity.getRecords().add(this);
		}
	}
	
	
}
