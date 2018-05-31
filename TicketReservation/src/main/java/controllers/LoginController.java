package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.AdminModel;
import business.model.BusAgencyModel;
import business.model.TravellerModel;
import business.model.UserModel;
import business.service.IAdminService;
import business.service.IBusAgencyService;
import business.service.ITravellerService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	private final IAdminService adminService;
	private final ITravellerService travellerService;
	private final IBusAgencyService agencyService;
	
	@Autowired
	public LoginController(IAdminService adminService, ITravellerService travellerService, IBusAgencyService agencyService) {
		this.adminService = adminService;
		this.travellerService = travellerService;
		this.agencyService = agencyService;
	}
	 
	@GetMapping("")
	public UserModel login(String email, String password) {
		try {
			AdminModel a = adminService.login(email, password);
			TravellerModel t = travellerService.login(email, password);
			BusAgencyModel b = agencyService.login(email, password);
			if(a != null) {
				return a;
			}
			else if(t != null){
				return t;
			}
			else {
				return b;
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	 }

}
