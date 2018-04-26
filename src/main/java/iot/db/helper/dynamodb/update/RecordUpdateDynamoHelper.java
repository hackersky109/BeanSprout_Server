package iot.db.helper.dynamodb.update;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Record;

public class RecordUpdateDynamoHelper extends DynamoDbMapperHelper<Record>{
	
	public RecordUpdateDynamoHelper(String tableName){
		super(tableName, Record.class);
	}

}
