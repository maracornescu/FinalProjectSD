package business.model;

public class AdminModel extends UserModel {
	
	private Long adminId;
	
	private String email;
	
	private String password;
	
	public AdminModel() {
		 
	 }
	
	public AdminModel(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
