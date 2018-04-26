package iot.service.api.bean;

import org.json.JSONArray;

public class SensorGroupBean extends TokenBean {
	private String sensorGroupName;
	private org.json.simple.JSONArray sensorList; 
	
	public SensorGroupBean() {
	}
	
	public boolean isValid() {
		return (sensorGroupName!=null) && (sensorList!=null) ;
	}
	
	public String getsensorGroupName() {
		return sensorGroupName;
	}
	
	public JSONArray getSensorList() {
		return new JSONArray(this.sensorList.toJSONString());
	}
}
