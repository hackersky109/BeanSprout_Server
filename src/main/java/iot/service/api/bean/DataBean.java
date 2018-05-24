package iot.service.api.bean;


import org.json.JSONArray;

import iot.db.model.Sensor;
import iot.service.api.bean.validation.Required;
import iot.utils.AbstractJSONObject;
import iot.utils.json.JSONConverter;

public class DataBean extends AbstractJSONObject{
	private String uploadKey;
	private String sensorId;
	private String userId;
	private String sensorType;
	private org.json.simple.JSONArray data; 
	
	public DataBean(){
		
	}
	
	public DataBean(JSONArray data, Sensor ss){
		this.sensorId = ss.getSensorId();
		this.sensorType = ss.getSensorType();
		this.data = JSONConverter.toSimpleJSONArray(data);
		this.userId = ss.getOwnerId();
	}
	
	public DataBean setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
		return this;
	}
	
	public DataBean setSensorId(String sensorId){
		this.sensorId = sensorId;
		return this;
	}
	
	public String getUploadKey(){
		return uploadKey;
	}
	
	public String getSensorId(){
		return sensorId;
	}
	
	public JSONArray getData() {
		return new JSONArray(this.data.toJSONString());
	}
	
	public DataBean setUserId(String userId){
		this.userId = userId;
		return this;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public DataBean setSensorType(String sensorType){
		this.sensorType = sensorType;
		return this;
	}
	
	public String getSensorType() {
		return sensorType;
	}
	public void setData(org.json.simple.JSONArray data){
		this.data = data;
	}

}
