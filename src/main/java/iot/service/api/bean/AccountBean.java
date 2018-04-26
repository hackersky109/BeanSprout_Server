package iot.service.api.bean;

public class AccountBean extends ApiBean {
	private String fcmtoken;
	private String name;
	private String password;
	private String email;
	
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

	public String getFcmtoken() {
		return fcmtoken;
	}

	public void setFcmtoken(String fcmtoken) {
		this.fcmtoken = fcmtoken;
	}
	
	
}
