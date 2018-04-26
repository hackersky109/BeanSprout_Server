package iot.db.model.query;

import java.util.List;

import iot.db.helper.dynamodb.query.RecordQueryDynamoHelper;
import iot.db.helper.dynamodb.query.SensorQueryDynamoHelper;
import iot.db.model.Record;

public class RecordQuery {
	private RecordQueryDynamoHelper queryHelper;
	public RecordQuery() {
		queryHelper = new RecordQueryDynamoHelper("Bean_Record");
	}
	
	public Record find(String recordId) {
		return queryHelper.loadByPk(recordId);
	}
	
	public List<Record> findAll(String sensorId) {
		return queryHelper.scanAttributeN("sensorId", sensorId);
	}
}
