package iot.db.model;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import iot.service.api.bean.SensorBean;
import iot.utils.AbstractJSONObject;
import iot.utils.Timestamp;

@DynamoDBTable(tableName="Bean_Sensor")
public class Sensor extends AbstractJSONObject{
    private String sensorId;
    private String sensorName;
    private String sensorType;
    private String ownerId;
    private String uploadKey;
    private Long createdtime;
    
    public Sensor(){}
    
    public Sensor(SensorBean bean){
    	this.sensorId = UUID.randomUUID().toString();
    	this.sensorName = bean.getsensorName();
    	this.sensorType = bean.getSensorType();
    	this.uploadKey = UUID.randomUUID().toString();
    	this.createdtime = Timestamp.now();
    }
    //Partition key
    @DynamoDBHashKey(attributeName="sensorId")
    public String getSensorId() { return sensorId; }    
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    
    @DynamoDBAttribute(attributeName="sensorName")
    public String getSensorName() { return sensorName; }
    public void setSensorName(String sensorName) { this.sensorName = sensorName; }
    
    @DynamoDBAttribute(attributeName="sensorType")
    public String getSensorType() { return sensorType; }    
    public void setSensorType(String sensorType) { this.sensorType = sensorType;}
    
    @DynamoDBAttribute(attributeName="ownerId")
    public String getOwnerId() { return ownerId; }    
    public void setOwnerId(String ownerId) { this.ownerId = ownerId;}
    
    @DynamoDBAttribute(attributeName="uploadKey")
    public String getUploadKey() { return uploadKey; }    
    public void setUploadKey(String uploadKey) { this.uploadKey = uploadKey;}
    
    @DynamoDBAttribute(attributeName="createdtime")
    public Long getCreatedtime() { return createdtime; }    
    public void setCreatedtime(Long createdtime) { this.createdtime = createdtime;}
    
}
