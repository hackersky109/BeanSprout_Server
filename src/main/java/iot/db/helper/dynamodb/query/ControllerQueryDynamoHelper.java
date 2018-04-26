package iot.db.helper.dynamodb.query;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Controller;



public class ControllerQueryDynamoHelper extends DynamoDbMapperHelper<Controller>{
	public ControllerQueryDynamoHelper(String tableName){
		super(tableName, Controller.class);
	}
}
