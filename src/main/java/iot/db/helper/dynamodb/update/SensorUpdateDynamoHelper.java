package iot.db.helper.dynamodb.update;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Sensor;

public class SensorUpdateDynamoHelper extends DynamoDbMapperHelper<Sensor>{
	
	public SensorUpdateDynamoHelper(String tableName){
		super(tableName, Sensor.class);
	}

}
