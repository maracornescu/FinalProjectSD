package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import repository.dbmodel.BusDto;
import repository.dbmodel.BusRouteDto;
import repository.dbmodel.StopsDto;

public interface IBusRouteRepository extends JpaRepository<BusRouteDto, Long> {
	
	public List<BusRouteDto> findByStop(StopsDto stop);
	public List<BusRouteDto> findByBus(BusDto bus);
	public BusRouteDto findByStopAndBus(StopsDto stop, BusDto bus);
	void deleteByStopAndBus(StopsDto stop, BusDto bus);
	
}
