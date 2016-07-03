package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.CarTypeService;
import cl.ubb.agil.service.exception.EmptyListException;

@RestController
@RequestMapping("/cartype")
public class CarTypeController {


	@Autowired
	private CarTypeService carTypeService;
	
	@RequestMapping(value= "/list" , method = GET)
	@ResponseBody
	public ResponseEntity<List<CarType>> getAllCarTypes(){
		ResponseEntity<List<CarType>> response;
		
		try{
			List<CarType> list = carTypeService.getAllCarTypes();
			response = new ResponseEntity<List<CarType>>(list, HttpStatus.OK);
		}
		catch(EmptyListException e){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
}
