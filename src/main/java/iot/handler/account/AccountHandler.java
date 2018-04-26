package iot.handler.account;



import iot.utils.exception.NotFoundException;
import org.json.simple.JSONObject;

import iot.db.model.Account;
import iot.db.model.query.AccountQuery;
import iot.db.model.update.AccountUpdate;
import iot.service.api.bean.AccountBean;
import iot.utils.exception.DuplicateException;
import iot.utils.jwt.JsonWebToken;
import iot.utils.jwt.JwtBuilder;

public class AccountHandler {
	private AccountUpdate update;
	private AccountQuery query;
	
	public AccountHandler() {
		update = new AccountUpdate();
		query= new AccountQuery();
	}
	
	public JSONObject createAccount(AccountBean bean) {
		if(query.existsByEmail(bean.getEmail()))
			throw new DuplicateException("Account has existed!");
		Account acc = update.create(bean);
//		JsonWebToken token = new JwtBuilder(acc.getUuid()).build();
		System.out.println("[createAccount] "+acc.toJSONObject().toJSONString());
		JSONObject body = acc.toJSONObject();
//		body.put("jwt", token.getJwt());
		return body;
	}
	
	public JSONObject login(AccountBean bean) {
		Account acc = new LoginValidator(bean).validate();
		JsonWebToken token = new JwtBuilder(acc.getUuid()).build();
		update.update(acc, bean);
		System.out.println("[Login] "+acc.getName()+"Email:"+acc.getEmail()+"token:"+token.getJwt()+"fcm_token:"+bean.getFcmtoken());
		JSONObject body = acc.toJSONObject();
		body.put("jwt", token.getJwt());
		return body;
	}
	
	public JSONObject getAccount(String uuid) {
		Account acc = query.find(uuid);
		if(acc == null) throw new NotFoundException("Account not found!");
		return acc.toJSONObject();
	}

}
