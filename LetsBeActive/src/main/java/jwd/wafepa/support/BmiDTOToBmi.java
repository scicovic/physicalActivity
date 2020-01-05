package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Bmi;


import jwd.wafepa.service.BmiService;

import jwd.wafepa.web.dto.BmiDTO;


@Component
public class BmiDTOToBmi implements Converter<BmiDTO, Bmi> {

	@Autowired
	private BmiService bmiService;
	
	
	@Override
	public Bmi convert(BmiDTO dto) {
		
			
			Bmi b = null;
			
			if(dto.getId() != null) {
				b = bmiService.findOne(dto.getId());
			}
			else {
				b = new Bmi();
			}
			
			b.setId(dto.getId());
			b.setDate(dto.getDate());
			b.setBmi(dto.getBmi());
		
			b.setNote(dto.getNote());
			
		
			
			return b;

	
}
	public List<Bmi> convert(List<BmiDTO> dto){
		List<Bmi> ret = new ArrayList<>();
		
		for(BmiDTO bmi : dto){
			ret.add(convert(bmi));
		}
		
		return ret;
	}
}
