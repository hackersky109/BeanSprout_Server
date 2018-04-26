package iot.db.model.query;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;

import iot.db.helper.dynamodb.query.DataQueryDynamoHelper;
import iot.db.helper.dynamodb.update.DataUpdateDynamoHelper;
import iot.db.model.Data;

public class DataQuery {
	private DataQueryDynamoHelper queryHelper;
	private TableNameOverride configoveride;
	private DynamoDBMapperConfig config;
	
	public DataQuery(String tableName){
		configoveride =  new DynamoDBMapperConfig.TableNameOverride(tableName);
		config = new DynamoDBMapperConfig(configoveride);
		queryHelper = new DataQueryDynamoHelper(tableName, config);	
	}

	public List<Data> getAllData(String partitionKey,String sensorId) {
		return queryHelper.batchLoad(partitionKey, sensorId, config);
	}
	
	public List<Data> getDataWithinTimePeriod(String partitionKey,String sensorId, String from, String to, int limit) {
		return queryHelper.queryWithinRangeKey(partitionKey, sensorId, from, to, limit, config);
	}

}
