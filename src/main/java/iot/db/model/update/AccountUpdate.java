package iot.db.model.update;



import iot.db.helper.dynamodb.update.AccountUpdateDynamoHelper;
import iot.db.model.Account;
import iot.service.api.bean.AccountBean;
import iot.utils.exception.BadRequestException;

public class AccountUpdate {
	private AccountUpdateDynamoHelper updateHelper;
	
	public AccountUpdate() {
		updateHelper = new AccountUpdateDynamoHelper("Bean_Account");
	}
	
	public Account create(AccountBean bean) {
		if(!bean.isValid()) 
			throw new BadRequestException("create account failed due to empty fields");
		Account acc = new Account(bean);
		updateHelper.saveItem(acc);
		return acc;
	}
	
	public void update(Account account, AccountBean bean) {
		if(bean.getFcmToken()!=null) {
			account.setFcmToken(bean.getFcmToken());
			updateHelper.saveItem(account);
		}
	}

}
