package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.BusAgencyDto;
import repository.dbmodel.BusDto;

@Repository
@Transactional
public interface IBusAgencyRepository extends JpaRepository<BusAgencyDto, Long> {
	
	public BusAgencyDto findByEmail(String email);
	public BusAgencyDto findByName(String name);
}
