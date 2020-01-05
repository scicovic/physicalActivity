package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Bmi;

import jwd.wafepa.service.BmiService;
import jwd.wafepa.support.BmiDTOToBmi;
import jwd.wafepa.support.BmiToBmiDTO;
import jwd.wafepa.web.dto.BmiDTO;


@RestController
@RequestMapping(value="/api/bmi")
public class ApiBmiController {

	
	@Autowired
	private BmiService bmiService;
	
	@Autowired
	private BmiToBmiDTO toDTO;
	
	@Autowired
	private BmiDTOToBmi toBmi;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<BmiDTO>> getBmi(@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Bmi> bmiPage = null;
		
		
		bmiPage = bmiService.findAll(pageNum);
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(bmiPage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDTO.convert(bmiPage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<BmiDTO> get(@PathVariable Long id){
		Bmi b = bmiService.findOne(id);
		if(b==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(b),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<BmiDTO> delete(@PathVariable Long id){
		Bmi deleted = bmiService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<BmiDTO> add(
			@Validated @RequestBody BmiDTO newDTO){
		
		Bmi saved = bmiService.save(
				toBmi.convert(newDTO));
		
		return new ResponseEntity<>(
				toDTO.convert(saved), 
				HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<BmiDTO> edit(
			@Validated @RequestBody BmiDTO DTO,
			@PathVariable Long id){
		
		if(!id.equals(DTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Bmi persisted = bmiService.save(
				toBmi.convert(DTO));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
}
