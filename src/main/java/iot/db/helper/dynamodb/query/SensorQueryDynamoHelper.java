package iot.db.helper.dynamodb.query;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Sensor;

public class SensorQueryDynamoHelper extends DynamoDbMapperHelper<Sensor>{
	
	public SensorQueryDynamoHelper(String tableName){
		super(tableName, Sensor.class);
	}
}
