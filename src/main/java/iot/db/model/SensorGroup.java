package iot.db.model;


import java.util.List;
import java.util.UUID;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


import iot.service.api.bean.SensorGroupBean;
import iot.utils.AbstractJSONObject;
import iot.utils.Timestamp;

@DynamoDBTable(tableName="SensorGroup")
public class SensorGroup extends AbstractJSONObject{
    private String sensorGroupId;
    private String sensorGroupName;
    private String ownerId;
    private List<Sensor> sensorList;
    private String uploadKey;
    private Long createdtime;
    
    public SensorGroup(){}
    
    public SensorGroup(SensorGroupBean bean, List<Sensor> sensorList){
    	this.sensorGroupId = UUID.randomUUID().toString();
    	this.sensorGroupName = bean.getsensorGroupName();
    	this.ownerId = bean.getUserId();
    	this.uploadKey = UUID.randomUUID().toString();
    	this.sensorList = sensorList;
    	this.createdtime = Timestamp.now();

    }
    //Partition key
    @DynamoDBHashKey(attributeName="sensorGroupId")
    public String getSensorGroupId() { return sensorGroupId; }    
    public void setSensorGroupId(String sensorGroupId) { this.sensorGroupId = sensorGroupId; }
    
    @DynamoDBAttribute(attributeName="sensorGroupName")
    public String getSensorGroupName() { return sensorGroupName; }
    public void setSensorGroupName(String sensorGroupName) { this.sensorGroupName = sensorGroupName; }
    
    @DynamoDBAttribute(attributeName="ownerId")
    public String getOwnerId() { return ownerId; }    
    public void setOwnerId(String ownerId) { this.ownerId = ownerId;}
    
    @DynamoDBAttribute(attributeName="sensorList")
    public List<Sensor> getSensorList() { return sensorList; }    
    public void setSensorList(List<Sensor> sensorList) { this.sensorList = sensorList;}
    
    @DynamoDBAttribute(attributeName="uploadKey")
    public String getUploadKey() { return uploadKey; }    
    public void setUploadKey(String uploadKey) { this.uploadKey = uploadKey;}
    
    @DynamoDBAttribute(attributeName="createdtime")
    public Long getCreatedtime() { return createdtime; }    
    public void setCreatedtime(Long createdtime) { this.createdtime = createdtime;}
      
}
