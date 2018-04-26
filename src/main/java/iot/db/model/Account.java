package iot.db.model;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import iot.service.api.bean.AccountBean;
import iot.utils.AbstractJSONObject;
import iot.utils.Timestamp;

@DynamoDBTable(tableName="Bean_Account")
public  class Account extends AbstractJSONObject{
    private String email;
    private String uuid;
    private String name;
    private String password;
    private Long createdtime;
    private String fcm_token;
    
    public Account(){}
    
    public Account(AccountBean bean){
    	this.uuid = UUID.randomUUID().toString();
    	this.email = bean.getEmail();
    	this.name = bean.getName();
    	this.password = bean.getPassword();
    	this.createdtime = Timestamp.now();
    }
    //Partition key
    @DynamoDBHashKey(attributeName="uuid")
    public String getUuid() { return uuid; }    
    public void setUuid(String uuid) { this.uuid = uuid; }
    
    @DynamoDBAttribute(attributeName="email")
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @DynamoDBAttribute(attributeName="name")
    public String getName() { return name; }    
    public void setName(String name) { this.name = name;}
    
    @DynamoDBAttribute(attributeName="password")
    public String getPassword() { return password; }    
    public void setPassword(String password) { this.password = password;}
    
    @DynamoDBAttribute(attributeName="createdtime")
    public Long getCreatedtime() { return createdtime; }    
    public void setCreatedtime(Long createdtime) { this.createdtime = createdtime;}
    
    @DynamoDBAttribute(attributeName="fcm_token")
    public String getFcmToken() { return fcm_token; }    
    public void setFcmToken(String fcm_token) { this.fcm_token = fcm_token;}
    
}
