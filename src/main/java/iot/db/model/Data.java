package iot.db.model;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import iot.service.api.bean.DataBean;
import iot.utils.AbstractJSONObject;

@DynamoDBTable(tableName="")
public class Data extends AbstractJSONObject{
    private String sensorId;
    private double value;
    private String sensorType;
    private long timestamp;
    
    public Data(){}
    
    public Data(DataBean bean){
    	this.sensorId = bean.getSensorId();
    	this.sensorType = bean.getSensorType();
    	
    }
    //Partition key
    @DynamoDBHashKey(attributeName="sensorId")
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    
    @DynamoDBRangeKey(attributeName="timestamp")  
    public long getTimestamp() { return timestamp; }    
    public void setTimestamp(long timestamp) { this.timestamp = timestamp;}
    
    public String getSensorType() { return sensorType; }    
    public void setSensorType(String sensorType) { this.sensorType = sensorType;}

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    
}