package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.TravellerDto;

@Repository
@Transactional
public interface ITravellerRepository extends JpaRepository<TravellerDto, Long> {
	
	public TravellerDto findByEmail(String email);

}
