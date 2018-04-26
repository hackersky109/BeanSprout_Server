package iot.db.helper.dynamodb.query;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.SensorGroup;

public class SensorGroupQueryDynamoHelper extends DynamoDbMapperHelper<SensorGroup>{

	public SensorGroupQueryDynamoHelper(String tableName) {
		super(tableName, SensorGroup.class);
	}

}
