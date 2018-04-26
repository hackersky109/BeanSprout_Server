package iot.utils.exception;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ErrorException extends RuntimeException {
	protected int errorCode = 400;
	
	public ErrorException() {
		
	}
	
	public ErrorException(String message) {
		super(message);
	}
	
	public String getJSONMessage() {
		return getJSONObject(errorCode).toString();
	}
	
	public JSONObject getJSONObject() {
		return getJSONObject(errorCode);
	}
	
	public JSONObject getJSONObject(int errorCode) {
		JSONObject obj = new JSONObject();
		obj.put("status", errorCode);
		obj.put("description", super.getMessage());
		return obj;
	}
}
