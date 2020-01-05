package jwd.wafepa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Record;

public interface RecordService {

	Record findOne(Long id);
	

	Page<Record> findAll(int pageNum);
	

	Record save(Record record);
	

	Record delete(Long id);
	


	
	Page<Record> search(
			@Param("activityName") String activityName, 
			@Param("minDuration") Integer minDuration, 
			@Param("intensity") String intensity,
			 int pageNum);
}
