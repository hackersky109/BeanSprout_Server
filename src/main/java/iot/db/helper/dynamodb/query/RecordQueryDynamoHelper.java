package iot.db.helper.dynamodb.query;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Record;

public class RecordQueryDynamoHelper extends DynamoDbMapperHelper<Record>{
	
	public RecordQueryDynamoHelper(String tableName){
		super(tableName, Record.class);
	}
}
