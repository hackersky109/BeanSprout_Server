package iot.db.model.query;

import java.util.List;

import iot.db.helper.dynamodb.query.SensorQueryDynamoHelper;
import iot.db.model.Sensor;

public class SensorQuery {
	private SensorQueryDynamoHelper queryHelper;
	public SensorQuery() {
		queryHelper = new SensorQueryDynamoHelper("Bean_Sensor");
	}
	
	public Sensor find(String sensorId) {
		return queryHelper.loadByPk(sensorId);
	}
	
	public List<Sensor> findAll(String userId) {
		return queryHelper.scanAttributeN("ownerId", userId);
	}
}
