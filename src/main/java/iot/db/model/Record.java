package iot.db.model;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import iot.service.api.bean.RecordBean;
import iot.utils.AbstractJSONObject;
import iot.utils.Timestamp;

@DynamoDBTable(tableName="Bean_Record")
public class Record extends AbstractJSONObject{
	private String recordId;
    private String sensorId;
    private String recordName;
    private Long startTime;
    private Long endTime;
    private double initialValue;
    private double targetValue;
    private Boolean inProgress;
    private Integer percentage;
    
    public Record(){}
    
    public Record(RecordBean bean){
    	this.recordId = UUID.randomUUID().toString();
    	this.sensorId = bean.getSensorId();
    	this.recordName = bean.getRecordName();
    	this.startTime = Timestamp.now();
    	this.endTime = null;
    	this.initialValue = 0;
    	this.targetValue = bean.getTargetValue();
    	this.inProgress = true;
    	this.percentage = 0;
    }
    //Partition key
    @DynamoDBHashKey(attributeName="recordId")
    public String getRecordId() { return recordId; }    
    public void setRecordId(String recordId) { this.recordId = recordId; }
    
    @DynamoDBAttribute(attributeName="recordName")
    public String getRecordName() { return recordName; }
    public void setRecordName(String recordName) { this.recordName = recordName; }
    
    @DynamoDBRangeKey(attributeName="startTime")
    public Long getStartTime() { return startTime; }    
    public void setStartTime(Long startTime) { this.startTime = startTime;}
    
    @DynamoDBAttribute(attributeName="endTime")
    public Long getEndTime() { return endTime; }    
    public void setEndTime(Long endTime) { this.endTime = endTime;}
    
    @DynamoDBAttribute(attributeName="sensorId")
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    
    @DynamoDBAttribute(attributeName="initialValue")
    public double getInitialValue() { return initialValue; }    
    public void setInitialValue(double initialValue) { this.initialValue = initialValue;}
    
    @DynamoDBAttribute(attributeName="inProgress")
    public Boolean getInProgress() { return inProgress; }    
    public void setInProgress(Boolean inProgress) { this.inProgress = inProgress;}
    
    @DynamoDBAttribute(attributeName="targetValue")
    public double getTargetValue() { return targetValue; }    
    public void setTargetValue(double targetValue) { this.targetValue = targetValue;}
    
    @DynamoDBAttribute(attributeName="percentage")
    public Integer getPercentage() { return percentage; }    
    public void setPercentage(Integer percentage) { this.percentage = percentage;}
    
}
