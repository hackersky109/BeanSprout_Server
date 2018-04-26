package iot.service.api.bean;

public class AccountBean extends ApiBean {
	private String name;
	private String password;
	private String email;
	private String fcm_token;
	
	public AccountBean() {
	}
	
	public boolean isValid() {
		return (password!=null) && (name!=null) && (email!=null);
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFcmToken() {
		return fcm_token;
	}
	
}
