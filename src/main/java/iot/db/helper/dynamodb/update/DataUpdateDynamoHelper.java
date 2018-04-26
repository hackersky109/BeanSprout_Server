package iot.db.helper.dynamodb.update;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Data;

public class DataUpdateDynamoHelper extends DynamoDbMapperHelper<Data>{
	public DataUpdateDynamoHelper(String tableName, DynamoDBMapperConfig config){
		super(tableName, Data.class, config);
	}

}
