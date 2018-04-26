package iot.service.api.bean;

public class RecordBean extends TokenBean {
	private String recordName;
	private String sensorId;
	private Float targetValue;
	
	public RecordBean() {
	}
	
	
	public String getRecordName() {
		return recordName;
	}
	
	public String getSensorId() {
		return sensorId;
	}
	
	public Float getTargetValue() {
		return targetValue;
	}
	
}
