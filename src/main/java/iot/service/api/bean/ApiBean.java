package iot.service.api.bean;

import iot.service.api.bean.validation.AuthApiKey;
import iot.service.api.bean.validation.Required;
import iot.service.api.bean.validation.RequiredAnnotationHandler;
import iot.utils.AbstractJSONObject;

public class ApiBean extends AbstractJSONObject {
	@Required(message="apiKey must not be null")
	@AuthApiKey
	protected String apiKey;
	
	public ApiBean() {}
	
	public ApiBean(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void validate(String apiKey) {
		this.apiKey = apiKey;
		validate();
	}
	
	public void validate() {
		new RequiredAnnotationHandler().validate(this);
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
