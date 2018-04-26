package iot.service.api.bean;

import iot.service.api.bean.validation.AuthToken;
import iot.service.api.bean.validation.Required;
import iot.service.api.bean.validation.RequiredAnnotationHandler;
import iot.utils.jwt.JsonWebToken;
import iot.utils.jwt.JwtParser;

public class TokenBean extends ApiBean {
	@Required(message="token must not be null")
	@AuthToken
	protected String token;
	protected JsonWebToken jwt;
	
	public TokenBean() {}
	
	public TokenBean(String apiKey, String token) {
		this.apiKey = apiKey;
		this.token = token;
	}
	
	public void validate() {
		new RequiredAnnotationHandler().validate(this);
	}
	
	public void validate(String apiKey, String token) {
		this.apiKey = apiKey;
		this.token = token;
		validate();
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public JsonWebToken getJwt() {
		if(jwt == null)
			jwt = new JwtParser().parse(token);
		return jwt;
	}
	
	public String getUserId() {
		JsonWebToken jwt = getJwt();
		return jwt.getAud();
	}
	
	public void setApiKeyAndToken(String apiKey, String token) {
		this.apiKey = apiKey;
		this.token = token;
	}
}
