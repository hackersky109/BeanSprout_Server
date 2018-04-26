package iot.db.model.query;

import iot.db.helper.dynamodb.query.AccountQueryDynamoHelper;
import iot.db.model.Account;

public class AccountQuery {
	private AccountQueryDynamoHelper queryHelper;
	public AccountQuery() {
		queryHelper = new AccountQueryDynamoHelper("Bean_Account");
	}
	
	public boolean exists(String uuid) {
		return queryHelper.existsByPk(uuid);
	}
	
	public Account find(String uuid) {
		return queryHelper.loadByPk(uuid);
	}
	
	public boolean existsByEmail(String email) {
		return !queryHelper.scanAttributeN("email", email).isEmpty();
	}
	
	public Account findByEmail(String email) {
		return queryHelper.scanAttributeN("email", email).get(0);
	}
}
