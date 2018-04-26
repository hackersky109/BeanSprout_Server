package iot.service.api.impl;

import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;

import iot.handler.controller.ControllerHandler;
import iot.service.api.bean.ControllerBean;
import iot.service.api.bean.TokenBean;
import iot.utils.exception.ErrorException;

public class ControllerApiImpl extends ApiImpl{
	public ResponseBuilder controll(TokenBean bean, String controllerId, String mode, Long delay_time) {
		try {
			bean.validate();
			body = new ControllerHandler().controll(bean.getUserId(), controllerId, mode, delay_time);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder newController(ControllerBean bean) {
		try {
			bean.validate();
			body = new ControllerHandler().newController(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder getControllerList(TokenBean bean) {
		try {
			bean.validate();
			body = new ControllerHandler().getSensorList(bean.getUserId());
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder getControllerMode(String controllerId) {
		try {
			body = new ControllerHandler().getControllerMode(controllerId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder updateConfig(TokenBean bean, String controllerId,org.json.simple.JSONObject config) {
		try {
			bean.validate();
			body = new ControllerHandler().updateConfig(bean.getUserId(), controllerId, config);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder getConfig(TokenBean bean, String controllerId) {
		try {
			bean.validate();
			body = new ControllerHandler().getConfig(bean.getUserId(), controllerId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}

}
