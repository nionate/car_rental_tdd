package cl.ubb.agil.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.service.BookingService;
@RestController
@RequestMapping("/booking")
public class BookingController {		
		@Autowired
		private BookingService bookingService;
		
		@RequestMapping(value = "/bookingACar/{customerRut}/{originPlace}/{startHour}/{destinyPlace}/{endHour}/{carTypeID}", method = POST)
		@ResponseBody
		public ResponseEntity<Integer> bookingACarWithExtras(
				@PathVariable("customerRut") String customerRut, 
				@PathVariable("originPlace") String originPlace,
				@PathVariable("startHour") String startHour,
				@PathVariable("destinyPlace") String destinyPlace,				
				@PathVariable("endHour") String endHour,
				@PathVariable("carTypeID") int carTypeID,
				@RequestParam(value = "startDate", required = true) String startDate,
				@RequestParam(value = "endDate", required = true) String endDate,
				final @RequestBody List<BookingExtra> extras
				){
			 
			 for(BookingExtra extra: extras){
				 System.out.println("extras " + extra.getExtraId() + "  " + extra.getNumber() );
			 }
			 System.out.println("RUT " + customerRut);
			 System.out.println("CARTYPEID " + carTypeID);
			 System.out.println("DATES " + startDate + " " + endDate);
			 System.out.println("hour " + endHour);
			 
			ResponseEntity<Integer> result = null;
			try {
				System.out.println("---------------");
				int aux = bookingService.booking(customerRut, originPlace, startDate, startHour, destinyPlace, endDate, endHour, carTypeID, extras);
				result = new ResponseEntity<Integer>(aux, HttpStatus.OK);
			} catch (ParseException e) {
				System.out.println("Las fechas no están en el formato dd/mm/aaaa");
			}
			
			System.out.println("RESULT " + result);
			return result;
		}
}