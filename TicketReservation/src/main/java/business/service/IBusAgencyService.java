package business.service;

import java.util.List;

import business.model.BusAgencyModel;

public interface IBusAgencyService {

	List<BusAgencyModel> getAllAgencies();
	BusAgencyModel getAgencyById(Long agencyId);
	BusAgencyModel saveAgency(BusAgencyModel agency);
    BusAgencyModel updateAgency(Long agencyId, BusAgencyModel agency); 
    void deleteAgencyById(Long agencyId);
	BusAgencyModel login(String email, String password);
	
}
