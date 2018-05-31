package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.model.BusAgencyModel;
import business.service.IBusAgencyService;

@RestController
@RequestMapping("agency")
public class BusAgencyController {
	
	private final IBusAgencyService busAgencyService;
	
	 @Autowired
	 public BusAgencyController(IBusAgencyService busAgencyService) {
		 this.busAgencyService = busAgencyService;
	 }

	 @GetMapping("")
	 public List<BusAgencyModel> getAllAgencies() {
		 try {
			 return busAgencyService.getAllAgencies();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }
	 
	@GetMapping("{id}")
	public BusAgencyModel getAgencyById(@PathVariable(value = "id") Long agencyId) {
		try {
			 return busAgencyService.getAgencyById(agencyId);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	
	@PostMapping("")
	public BusAgencyModel saveAgency(@RequestBody BusAgencyModel agency) {
		try {
          return busAgencyService.saveAgency(agency);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@PutMapping("{id}")
	public BusAgencyModel updateAgency(@PathVariable(value = "id") Long agencyId, BusAgencyModel agency) {
		try {
          return busAgencyService.updateAgency(agencyId, agency);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@DeleteMapping("{id}")
	public String deleteAgencyById(@PathVariable(value = "id") Long agencyId) {
		try {
			busAgencyService.deleteAgencyById(agencyId);
	        return "Agency with id = " + agencyId + " has been successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
}
