package iot.db.helper.dynamodb.update;

import iot.db.helper.dynamodb.DynamoDbMapperHelper;
import iot.db.model.Account;

public class AccountUpdateDynamoHelper extends DynamoDbMapperHelper<Account>{
	
	public AccountUpdateDynamoHelper(String tableName){
		super(tableName, Account.class);
	}

}
