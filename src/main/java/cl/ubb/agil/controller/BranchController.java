package cl.ubb.agil.controller;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.BranchService;
import cl.ubb.agil.service.exception.EmptyListException;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@RequestMapping(value = "/list", method = GET)
	@ResponseBody
	public ResponseEntity<List<Branch>> getAllBranches(){
		ResponseEntity<List<Branch>> response;
		
		try{
			List<Branch> list = branchService.getAll();
			response = new ResponseEntity<List<Branch>>(list, HttpStatus.OK);
		}catch(EmptyListException ex){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
}
