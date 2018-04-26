package iot.service.api.bean;

import iot.handler.sensor.SensorType;

public class SensorBean extends TokenBean {
	private String sensorName;
	private String sensorType;
	private Boolean typeValid = false;
	
	public SensorBean() {
	}
	
	public boolean isValid() {
		checkSensorType() ;
		return (sensorName!=null) && typeValid ;
	}
	
	public String getsensorName() {
		return sensorName;
	}
	
	public String getSensorType() {
		return sensorType;
	}
	
	private void checkSensorType() {
		for(SensorType type : SensorType.values())
			if(type.toString().equals(sensorType)) typeValid=true ;
	}
	
}
