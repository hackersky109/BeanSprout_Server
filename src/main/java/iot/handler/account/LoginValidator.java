package iot.handler.account;

import iot.db.model.Account;
import iot.db.model.query.AccountQuery;
import iot.db.model.update.AccountUpdate;
import iot.service.api.bean.AccountBean;
import iot.utils.exception.NotFoundException;

public class LoginValidator {
	private AccountQuery accountQuery;
	private AccountUpdate accountUpdate;
	private AccountBean loginInfo;
	
	public LoginValidator(AccountBean bean) {
		this.loginInfo = bean;
		this.accountQuery = new AccountQuery();
		this.accountUpdate = new AccountUpdate();
	}
	
	public Account validate() {
		Account acc;
		try {
			acc = accountQuery.findByEmail(loginInfo.getEmail());
		}catch (Exception e) {
			throw new NotFoundException("Account not found");
		}	
		if(!acc.getPassword().equals(loginInfo.getPassword()))
			throw new NotFoundException("Wrong email/password");
		return acc;
	}
	
}
