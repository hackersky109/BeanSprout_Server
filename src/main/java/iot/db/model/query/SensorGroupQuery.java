package iot.db.model.query;

import iot.db.helper.dynamodb.query.SensorGroupQueryDynamoHelper;
import iot.db.model.SensorGroup;


public class SensorGroupQuery {
	private SensorGroupQueryDynamoHelper queryHelper;
	
	public SensorGroupQuery() {
		queryHelper = new SensorGroupQueryDynamoHelper("SensorGroup");
	}
	
	public SensorGroup find(String sensorGroupId) {
		return queryHelper.loadByPk(sensorGroupId);
	}

}
