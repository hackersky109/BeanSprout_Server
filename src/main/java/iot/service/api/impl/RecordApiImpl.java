package iot.service.api.impl;

import javax.ws.rs.core.Response.ResponseBuilder;

import iot.handler.record.RecordHandler;
import iot.service.api.bean.RecordBean;
import iot.service.api.bean.TokenBean;
import iot.utils.exception.ErrorException;

public class RecordApiImpl extends ApiImpl{
	public ResponseBuilder newRecord(RecordBean bean) {
		try {
			bean.validate();
			body = new RecordHandler().newRecord(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	

	public ResponseBuilder getRecord(TokenBean bean, String recordId) {
		try {
			bean.validate();
			body = new RecordHandler().getRecord(recordId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder getRecordList(TokenBean bean, String sensorId) {
		try {
			bean.validate();
			body = new RecordHandler().getRecordList(sensorId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder deleteRecord(TokenBean bean, String recordId) {
		try {
			bean.validate();
			body = new RecordHandler().deleteRecord(recordId);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
}
