package business.service;

import java.util.List;

import business.model.BusRouteModel;

public interface IBusRouteService {
	
	List<BusRouteModel> getAllRoutes();
	List<BusRouteModel> getAllRoutesByBus(Long busId);
	List<BusRouteModel> getAllRoutesByStop(Long stopId);
	BusRouteModel getAllRoutesByBusAndStop(Long busId, Long stopId);
	BusRouteModel saveRoute(BusRouteModel route);
	void deleteRouteById(Long routeId);
	void deleteRouteByBusAndStop(Long busId, Long stopId);
	
}
