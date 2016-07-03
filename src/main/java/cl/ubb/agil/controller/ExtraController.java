package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.ExtraService;
import cl.ubb.agil.service.exception.EmptyListException;

@RestController
@RequestMapping("/extra")
public class ExtraController {
	@Autowired
	private ExtraService extraService;
	
	@RequestMapping(value = "/list", method = GET)
	@ResponseBody
	public ResponseEntity<List<Extra>> getAllExtras(){
		ResponseEntity<List<Extra>> response;
		
		try{
			List<Extra> list = extraService.getAll();
			response = new ResponseEntity<List<Extra>>(list, HttpStatus.OK);
		}catch(EmptyListException ex){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
}
