package business.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.model.AdminModel;
import repository.IAdminRepository;
import repository.dbmodel.AdminDto;

@Service
public class AdminService implements IAdminService{
	
	public final IAdminRepository adminRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public AdminService(IAdminRepository adminRepository) {
		this.adminRepository = adminRepository;
		modelMapper = new ModelMapper();
	}
	
	public AdminModel login(String email, String password) {
		AdminDto loginAdmin = adminRepository.findByEmail(email);
		
		if(loginAdmin != null) {
			if(loginAdmin.getPassword().equals(password)) {
				AdminModel adminModel = modelMapper.map(loginAdmin, AdminModel.class);
				return adminModel;
			}
		}
		return null;
	}

}
