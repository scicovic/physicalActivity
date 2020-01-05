package jwd.wafepa.service;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Bmi;


public interface BmiService {


	Bmi findOne(Long id);
	
	
	Page<Bmi> findAll(int pageNum);
	

	Bmi save(Bmi bmi);
	

	Bmi delete(Long id);
}
