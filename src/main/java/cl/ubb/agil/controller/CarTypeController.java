package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import cl.ubb.agil.model.BookingExtra;
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
	
	@RequestMapping(value="/value/{identifier}",method = POST)
	@ResponseBody
	public ResponseEntity<Integer> priceOfTypeCar(
			@PathVariable("identifier") int identifier,
			@RequestParam(value = "startDate", required=true) String startDate,
			@RequestParam(value = "endDate", required=true) String endDate,
			final @RequestBody List<BookingExtra> extras
			){
				ResponseEntity<Integer> result = null;
				
				try{
					int price = carTypeService.getPrice(startDate, endDate, identifier, extras);
					result = new ResponseEntity<Integer>(price,HttpStatus.OK);
				}catch(java.text.ParseException e){
					System.out.println("Las fechas no están en el formato dd/mm/aaaa");
				}
				
				return result;
		
	}
	
}
