package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import jwd.wafepa.model.Bmi;

import jwd.wafepa.web.dto.BmiDTO;

@Component
public class BmiToBmiDTO implements Converter<Bmi, BmiDTO> {

	@Override
	public BmiDTO convert(Bmi b) {
		if(b==null){
			return null;
		}
		
		BmiDTO dto = new BmiDTO();
		
		dto.setId(b.getId());
		dto.setDate(b.getDate());
		dto.setBmi(b.getBmi());
		dto.setNote(b.getNote());
		return dto;
	}
	
	public List<BmiDTO> convert(List<Bmi> bmis){
		List<BmiDTO> ret = new ArrayList<>();
		
		for(Bmi a: bmis){
			ret.add(convert(a));
		}
		
		return ret;
	}

	
}
