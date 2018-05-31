package business.service;

import java.util.List;
import business.model.StopsModel;

public interface IStopsService {
	
	public List<StopsModel> getAllStops();
	public StopsModel saveStop(StopsModel stop);
    public void deleteStopById(Long stopId);
	
}
