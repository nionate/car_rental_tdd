package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.http.HttpStatus.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.Customer;
import cl.ubb.agil.service.CustomerService;
import cl.ubb.agil.service.exception.CreateException;



@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/create", method= POST)
	@ResponseBody
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		ResponseEntity<Customer> response;
		
		try{
			customerService.registerCustomer(customer);
			response = new ResponseEntity<>(customer,CREATED);
		}catch(CreateException e){
			response = new ResponseEntity<>(NOT_ACCEPTABLE);
		}
		return response;
	}
	


}
