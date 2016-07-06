package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.service.SanctionService;
@RestController
@RequestMapping("/sanction")
public class SanctionController {		
		@Autowired
		private SanctionService sanctionService;
		
		@RequestMapping(value = "/haveActiveSanctionByCustomer/{rutCustomer}/", method = GET)
		@ResponseBody
		public Map<String,Boolean> searchActiveSanctionByCustomer(@PathVariable("rutCustomer") String rutCustomer, 
				@RequestParam(value="date",required = true) String date){
			boolean result = sanctionService.searchUserWithSanction(rutCustomer, date);
			return Collections.singletonMap("result", result);
		}
}


