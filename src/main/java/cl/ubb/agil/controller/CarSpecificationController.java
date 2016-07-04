package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.service.CarSpecificationService;
import cl.ubb.agil.service.exception.EmptyListException;

@RestController
@RequestMapping("/carspecification")
public class CarSpecificationController {
	
	@Autowired
	private CarSpecificationService carSpecificationService;
	
	@RequestMapping(value="/listbyid/{idCartype}", method=GET)
	@ResponseBody
	public ResponseEntity<List<CarSpecification>> getListCarSpecificationByCarTypeId(@PathVariable("idCartype")int idCartype){
		ResponseEntity<List<CarSpecification>> response;
		
		try{
			List<CarSpecification> list = new ArrayList<CarSpecification>();
			list = carSpecificationService.getAllCarsByType(idCartype);
			response = new ResponseEntity<>(list,OK);
			
		}catch(EmptyListException e){
			response = new ResponseEntity<>(NOT_FOUND);
		}
		return response;
	}

	
}
