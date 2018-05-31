package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.BusAgencyDto;
import repository.dbmodel.BusDto;

@Repository
@Transactional
public interface IBusRepository extends JpaRepository<BusDto, Long> {
	
	public BusDto findByFromCityAndToCityAndBusAgencyAndDate(String fromCity, String toCity, BusAgencyDto busAgency, String date);
	public List<BusDto> findByDate(String date);
	public List<BusDto> findByBusAgency(BusAgencyDto busAgency);
	public List<BusDto> findByDateAndBusAgency(String date, BusAgencyDto busAgency);
	//public List<BusDto> findByBusAgency(BusAgencyDto busAgency);

}
