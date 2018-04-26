package iot.db.helper.dynamodb.query;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Data;

public class DataQueryDynamoHelper extends DynamoDbMapperHelper<Data>{
	public DataQueryDynamoHelper(String tableName, DynamoDBMapperConfig config){
		super(tableName, Data.class, config);
	}

}
