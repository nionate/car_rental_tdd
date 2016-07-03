package cl.ubb.agil.controller;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.CustomerCategoryService;
import cl.ubb.agil.service.exception.EmptyListException;

@RestController
@RequestMapping("/customerCategory")
public class CustomerCategoryController {
	@Autowired
	private CustomerCategoryService customerCategoryService;
	
	@RequestMapping(value = "/list", method = GET)
	@ResponseBody
	public ResponseEntity<List<CustomerCategory>> getAllCategories(){
		ResponseEntity<List<CustomerCategory>> response;
		
		try{
			List<CustomerCategory> list = customerCategoryService.getAllCategories();
			response = new ResponseEntity<List<CustomerCategory>>(list, HttpStatus.OK);
		}catch(EmptyListException ex){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
}
