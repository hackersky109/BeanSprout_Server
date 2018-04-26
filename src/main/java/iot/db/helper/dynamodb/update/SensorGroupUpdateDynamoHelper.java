package iot.db.helper.dynamodb.update;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.SensorGroup;

public class SensorGroupUpdateDynamoHelper extends DynamoDbMapperHelper<SensorGroup>{
	public SensorGroupUpdateDynamoHelper(String tableName){
		super(tableName, SensorGroup.class);
	}
}
