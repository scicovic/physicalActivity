package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Record;

import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.RecordService;


@Component
public class TestData {

	@Autowired
	private ActivityService activityService;
	
	
	@Autowired
	private RecordService recordService;
	
	@PostConstruct
	public void init() {
		Activity activity1 = activityService.save(new Activity("Plivanje"));
		Activity activity2 = activityService.save(new Activity("Trčanje"));
		
		
		
		
		Record record1 = new Record();
		record1.setTime("21/10/2018 08:00");
		record1.setDuration(60);
		record1.setIntensity("Slabiji");
		
		record1.setActivity(activity1);
		recordService.save(record1);
		
		Record record2 = new Record();
		record2.setTime("10/11/2019 08:00");
		record2.setDuration(60);
		record2.setIntensity("Srednji");
		
		record2.setActivity(activity2);
		recordService.save(record2);
	}
}
