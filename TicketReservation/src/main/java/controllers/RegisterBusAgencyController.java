package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.BusAgencyModel;
import business.service.IBusAgencyService;

@RestController
@RequestMapping("registerAgency")
public class RegisterBusAgencyController {
	
	private final IBusAgencyService busAgencyService;
	
	 @Autowired
	 public RegisterBusAgencyController(IBusAgencyService busAgencyService) {
		 this.busAgencyService = busAgencyService;
	 }
	 
	 @PostMapping("")
	 public BusAgencyModel registerAgency(@RequestBody BusAgencyModel agency) {
		 try {
			 return busAgencyService.saveAgency(agency);
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }
	 }

}
