package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repository.dbmodel.AdminDto;

@Repository
@Transactional
public interface IAdminRepository extends JpaRepository<AdminDto, Long> {
	
	public AdminDto findByEmail(String email);
	
}
