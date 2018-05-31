package repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.BusDto;
import repository.dbmodel.TicketDto;
import repository.dbmodel.TravellerDto;

@Repository
@Transactional
public interface ITicketRepository extends JpaRepository<TicketDto, Long> {

	List<TicketDto> findByBus(BusDto bus);
	List<TicketDto> findByTraveller(TravellerDto traveller);
	TicketDto findByBusAndTraveller(BusDto bus, TravellerDto traveller);
}
