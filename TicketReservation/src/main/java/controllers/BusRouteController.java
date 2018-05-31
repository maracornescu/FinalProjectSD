package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.model.BusRouteModel;
import business.service.IBusRouteService;

@RestController
@RequestMapping("route")
public class BusRouteController {
	
	private final IBusRouteService busRouteService;
	
	@Autowired
	public BusRouteController(IBusRouteService busRouteService) {
		 this.busRouteService = busRouteService;
	}
	
	@GetMapping("")
	public List<BusRouteModel> getRoute(@RequestParam(required = false) Long busId, @RequestParam(required = false) Long stopId) {
		 try {
			 if(busId == null && stopId == null) {
				 return busRouteService.getAllRoutes();
			 }
			 else if(busId == null) {
				 return busRouteService.getAllRoutesByStop(stopId);
			 }
			 else if (stopId == null) {
				 return busRouteService.getAllRoutesByBus(busId);
			 }
			 else {
				 BusRouteModel m = busRouteService.getAllRoutesByBusAndStop(busId, stopId);
				 List<BusRouteModel> s = new ArrayList<BusRouteModel>();
				 s.add(m);
				 return s;
			 }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	}
	
	
	@PostMapping("")
	public BusRouteModel saveRoute(@RequestBody BusRouteModel route) {
		try {
          return busRouteService.saveRoute(route);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
	}
	
	@DeleteMapping("{id}")
	public String deleteRouteById(@PathVariable(value = "id") Long routeId) {
		try {
			busRouteService.deleteRouteById(routeId);
	        return "Route with id = " + routeId + " has been successfully deleted!";
	    } catch (Exception e) {
	    	return e.getMessage();
	    }
	}
	
}


