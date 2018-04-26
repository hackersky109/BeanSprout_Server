package iot.service.api.bean;

import iot.service.api.bean.validation.Required;

public class ControllerBean extends TokenBean{
	@Required(message="controllerName must not be null")
	private String controllerName;
	private String macAddr;
	
	public ControllerBean(){
	}
	
	public String getControllerName() {
		return controllerName;
	}
	
	public String getMacAddr() {
		return macAddr;
	}
}
