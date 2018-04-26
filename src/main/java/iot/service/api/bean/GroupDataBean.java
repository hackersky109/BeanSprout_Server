package iot.service.api.bean;

import org.json.JSONArray;

import iot.service.api.bean.validation.Required;
import iot.utils.AbstractJSONObject;

public class GroupDataBean extends AbstractJSONObject{
	@Required(message="uploadKey must not be null")
	private String uploadKey;
	private String sensorGroupId;
	private org.json.simple.JSONArray dataList; 
	
	public GroupDataBean setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
		return this;
	}
	
	public GroupDataBean setSensorGroupId(String sensorGroupId){
		this.sensorGroupId = sensorGroupId;
		return this;
	}
	
	public String getSensorGroupId(){
		return sensorGroupId;
	}
	
	public String getUploadKey(){
		return uploadKey;
	}
	
	public JSONArray getDataList() {
		return new JSONArray(this.dataList.toJSONString());
	}

}
