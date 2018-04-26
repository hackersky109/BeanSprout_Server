package iot.db.helper.dynamodb.update;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Controller;

public class ControllerUpdateDynamoHelper extends DynamoDbMapperHelper<Controller>{
	public ControllerUpdateDynamoHelper(String tableName){
		super(tableName, Controller.class);
	}
}
