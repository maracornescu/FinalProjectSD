package business.service;

import business.model.AdminModel;

public interface IAdminService {
	
	AdminModel login(String email, String password);

}
