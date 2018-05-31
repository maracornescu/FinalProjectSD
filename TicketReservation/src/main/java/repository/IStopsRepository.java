package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.StopsDto;

@Repository
@Transactional
public interface IStopsRepository extends JpaRepository<StopsDto, Long> {
	
	public StopsDto findByStopName(String stopName);
}
