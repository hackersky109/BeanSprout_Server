package iot.service.api.impl;

import javax.ws.rs.core.Response.ResponseBuilder;

import iot.handler.sensor.SensorGroupHandler;
import iot.service.api.bean.GroupDataBean;
import iot.service.api.bean.SensorGroupBean;
import iot.utils.exception.ErrorException;

public class SensorGroupApiImpl extends ApiImpl{
	public ResponseBuilder newSensorGroup(SensorGroupBean bean) {
		try {
			bean.validate();
			body = new SensorGroupHandler().newSensorGroup(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder newData(GroupDataBean bean) {
		try {
			body = new SensorGroupHandler().addData(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}

}
