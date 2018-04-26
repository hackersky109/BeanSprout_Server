package iot.db.model;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;

import iot.service.api.bean.ControllerBean;
import iot.utils.AbstractJSONObject;
import iot.utils.Timestamp;

@DynamoDBTable(tableName="Controller")
public class Controller extends AbstractJSONObject{
    private String controllerName;
    private String controllerId;
    private String ownerId;
    private String macAddr;
    private String mode;
    private Long createdtime;
    private Long delay_time;
    private Map<String, String> config;
    
	public Controller(){}
	
	public Controller(ControllerBean bean){
		this.controllerId = UUID.randomUUID().toString();
		this.controllerName = bean.getControllerName();
		if(bean.getMacAddr()!=null){
			this.macAddr = bean.getMacAddr();
		}
		this.mode = "off";
		this.delay_time = 300000L;
		this.createdtime = Timestamp.now();
		this.config = new HashMap<String, String>();
		this.config.put("irrigation_threshold", "undefined");
		this.config.put("irrigation_time", "undefined");
	}
	
    //Partition key
    @DynamoDBHashKey(attributeName="controllerId")
    public String getControllerId() { return controllerId; }    
    public void setControllerId(String controllerId) { this.controllerId = controllerId; }
    
    @DynamoDBAttribute(attributeName="macAddr")
    public String getMacAddr() { return macAddr; }    
    public void setMacAddr(String macAddr) { this.macAddr = macAddr; }
    
    @DynamoDBAttribute(attributeName="controllerName")
    public String getControllerName() { return controllerName; }    
    public void setControllerName(String controllerName) { this.controllerName = controllerName;}
    
    @DynamoDBAttribute(attributeName="ownerId")
    public String getOwnerId() { return ownerId; }    
    public void setOwnerId(String ownerId) { this.ownerId = ownerId;}
    
    @DynamoDBAttribute(attributeName="mode")
    public String getMode() { return mode; }    
    public void setMode(String mode) { this.mode = mode;}
       
    @DynamoDBAttribute(attributeName="createdtime")
    public Long getCreatedtime() { return createdtime; }    
    public void setCreatedtime(Long createdtime) { this.createdtime = createdtime;}
    
    @DynamoDBAttribute(attributeName="delay_time")
    public Long getDelaytime() { return delay_time; }    
    public void setDelaytime(Long delay_time) { this.delay_time = delay_time;}
    
    @DynamoDBAttribute(attributeName="config")
    public Map<String, String> getConfig() { return config; }
    public void setConfig(Map<String, String> config) { this.config = config; }
    
}



