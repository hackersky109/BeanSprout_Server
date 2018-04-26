package iot.db.helper.dynamodb.query;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Account;

public class AccountQueryDynamoHelper extends DynamoDbMapperHelper<Account>{
	
	public AccountQueryDynamoHelper(String tableName){
		super(tableName, Account.class);
	}
	
}
