package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Bmi;
import jwd.wafepa.repository.BmiRepository;

import jwd.wafepa.service.BmiService;

@Service
public class JpaBmiService implements BmiService {
	
	@Autowired
	private BmiRepository bmiRepository;
	
	@Override
	public Bmi findOne(Long id) {
		return bmiRepository.findOne(id);
	}

	@Override
	public Page<Bmi> findAll(int pageNum) {
		return bmiRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Bmi save(Bmi bmi) {
		return bmiRepository.save(bmi);
	}

	@Override
	public Bmi delete(Long id) {
		Bmi bmi = bmiRepository.findOne(id);
		if(bmi != null){
			bmiRepository.delete(bmi);
		}
		
		return bmi;
	}

	

}
