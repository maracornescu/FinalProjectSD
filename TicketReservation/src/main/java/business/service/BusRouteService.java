package business.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.BusRouteModel;
import repository.IBusRouteRepository;
import repository.dbmodel.BusDto;
import repository.dbmodel.BusRouteDto;
import repository.dbmodel.StopsDto;

@Service
public class BusRouteService implements IBusRouteService{
	
	public final IBusRouteRepository busRouteRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public BusRouteService(IBusRouteRepository busRouteRepository) {
		this.busRouteRepository = busRouteRepository;
		modelMapper = new ModelMapper();
	}

	public List<BusRouteModel> getAllRoutes() {
		List<BusRouteModel> routeModel = new ArrayList<BusRouteModel>();
		List<BusRouteDto> routeDto = busRouteRepository.findAll();
	
		for(BusRouteDto s: routeDto) {
			BusRouteModel route = new BusRouteModel();
			route.setBusRouteId(s.getBusRouteId());
			route.setBusId(s.getBus().getBusId());
			route.setStopId(s.getStop().getStopId());
			route.setDistance(s.getDistance());
			route.setHour(s.getHour());
			route.setFare(s.getFare());
			//BusRouteModel route = modelMapper.map(s, BusRouteModel.class);
			routeModel.add(route);
		}
		
		return routeModel;
	}
	
	
	public List<BusRouteModel> getAllRoutesByBus(Long busId) {
		BusDto  busDto = new BusDto(busId);
		
		List<BusRouteModel> routeModel = new ArrayList<BusRouteModel>();
		List<BusRouteDto> routeDto = busRouteRepository.findByBus(busDto);
		
		for(BusRouteDto s: routeDto) {
			BusRouteModel route = new BusRouteModel();
			route.setBusRouteId(s.getBusRouteId());
			route.setBusId(s.getBus().getBusId());
			route.setStopId(s.getStop().getStopId());
			route.setDistance(s.getDistance());
			route.setHour(s.getHour());
			route.setFare(s.getFare());
			//BusRouteModel route = modelMapper.map(s, BusRouteModel.class);
			routeModel.add(route);
		}
		
		return routeModel;
	}
	
	public List<BusRouteModel> getAllRoutesByStop(Long stopId) {
		StopsDto stopDto = new StopsDto(stopId);
		
		List<BusRouteModel> routeModel = new ArrayList<BusRouteModel>();
		List<BusRouteDto> routeDto = busRouteRepository.findByStop(stopDto);
		
		for(BusRouteDto s: routeDto) {
			BusRouteModel route = modelMapper.map(s, BusRouteModel.class);
			routeModel.add(route);
		}
		
		return routeModel;
	}
	
	public BusRouteModel getAllRoutesByBusAndStop(Long busId, Long stopId) {
		BusDto  busDto = new BusDto(busId);
		StopsDto stopDto = new StopsDto(stopId);
		
		BusRouteDto routeDto = busRouteRepository.findByStopAndBus(stopDto, busDto);
		BusRouteModel routeModel = modelMapper.map(routeDto, BusRouteModel.class);
		
		return routeModel;
	}
	
	public BusRouteModel saveRoute(BusRouteModel route) {

		BusRouteDto routeDto = modelMapper.map(route, BusRouteDto.class);
    	
		StopsDto stopDto = new StopsDto(route.getStopId());
		BusDto busDto = new BusDto(route.getBusId());
		
        if (busRouteRepository.findByStopAndBus(stopDto, busDto) == null) {
        	busRouteRepository.save(routeDto);
            return route;
        }
        
        return null;
    }
	
    public void deleteRouteById(Long routeId) {
    	busRouteRepository.deleteById(routeId);   
    }
    
    public void deleteRouteByBusAndStop(Long busId, Long stopId) {
		StopsDto stopDto = new StopsDto(busId);
		BusDto busDto = new BusDto(stopId);
		
		busRouteRepository.deleteByStopAndBus(stopDto, busDto);
    }
	
}
