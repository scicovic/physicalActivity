package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Activity;

public interface ActivityService {
	
	Activity findOne(Long id);
	
	List<Activity> findAll();
	
	Activity save(Activity activity);
	
	List<Activity> save(List<Activity> activities);
	
	Activity delete(Long id);
	
	
	void delete(List<Long> ids);
	
	List<Activity> findByName(String name);
	
	
	
	
	
}
