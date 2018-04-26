package iot.service.api.impl;

import javax.ws.rs.core.Response.ResponseBuilder;


import iot.handler.sensor.SensorHandler;
import iot.service.api.bean.DataBean;
import iot.service.api.bean.SensorBean;
import iot.service.api.bean.TokenBean;
import iot.utils.exception.ErrorException;

public class SensorApiImpl extends ApiImpl{
	public ResponseBuilder newSensor(SensorBean bean) {
		try {
			bean.validate();
			body = new SensorHandler().newSensor(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}

	public ResponseBuilder getSensor(TokenBean bean, String sensorId) {
		try {
			bean.validate();
			body = new SensorHandler().getSensor(sensorId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder getSensorList(TokenBean bean) {
		try {
			bean.validate();
			body = new SensorHandler().getSensorList(bean.getUserId());
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder newData(DataBean bean) {
		try {
			body = new SensorHandler().addData(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}

	public ResponseBuilder geData(TokenBean bean, String sensorId,String from, String to) {
		try{
			bean.validate();
			body = new SensorHandler().getData(sensorId, from, to);
			body.put("status", 200);
		}catch(ErrorException e ){
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder deleteSensor(TokenBean bean, String sensorId) {
		try {
			bean.validate();
			body = new SensorHandler().deleteSensor(bean, sensorId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
}
