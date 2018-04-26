package iot.db.helper.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.TableDescription;


import iot.db.connet.dynamodb.setting.DynamoDBClientFactory;
import iot.utils.exception.NotFoundException;

public class DynamoDbHelper {
	DynamoDB dynamoDB;
	Table table;
	DynamoDBMapper mapper;
	public <M> DynamoDbHelper(String tableName, Class<M> m){	
		dynamoDB  = new DynamoDB(DynamoDBClientFactory.getDynamoDBClient());
		table = dynamoDB.getTable(tableName);
		mapper = new DynamoDBMapper(DynamoDBClientFactory.getDynamoDBClient());
		checkTableExists(tableName, m);
	}
	
	public <M> DynamoDbHelper(String tableName, Class<M> m, DynamoDBMapperConfig config){	
		dynamoDB  = new DynamoDB(DynamoDBClientFactory.getDynamoDBClient());
		table = dynamoDB.getTable(tableName);
		mapper = new DynamoDBMapper(DynamoDBClientFactory.getDynamoDBClient());
		checkTableExists(tableName, m, config);
	}
	
	private <M> void checkTableExists(String tableName, Class<M> m){
		try{
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
        TableDescription tableDescription = DynamoDBClientFactory.getDynamoDBClient().describeTable(describeTableRequest).getTable();
        System.out.println("Table Description: " + tableDescription);
		}catch(com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException e){
			CreateTableRequest req = mapper.generateCreateTableRequest(m);
			// Table provision throughput is still required since it cannot be specified in your POJO
			req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
			// Fire off the CreateTableRequest using the low-level client
			try {
				dynamoDB.createTable(req).waitForActive();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private <M> void checkTableExists(String tableName, Class<M> m, DynamoDBMapperConfig config){
		try{
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
        TableDescription tableDescription = DynamoDBClientFactory.getDynamoDBClient().describeTable(describeTableRequest).getTable();
        System.out.println("Table Description: " + tableDescription);
		}catch(com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException e){
			CreateTableRequest req = mapper.generateCreateTableRequest(m, config);
			// Table provision throughput is still required since it cannot be specified in your POJO
			req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
			// Fire off the CreateTableRequest using the low-level client
			try {
				dynamoDB.createTable(req).waitForActive();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	}

}
