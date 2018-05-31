package business.service;

import java.util.List;

import business.model.BusModel;

public interface IBusService {
	
	List<BusModel> getAllBusses();
	List<BusModel> getAllByAgency(Long agencyId);
	BusModel getBusById(Long busId);
	BusModel saveBus(BusModel bus);
	BusModel updateBus(Long busId, BusModel bus);
	void deleteBusById(Long busId);

}
