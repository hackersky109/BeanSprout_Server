package iot.service.api.impl;

import javax.ws.rs.core.Response.ResponseBuilder;

import iot.service.api.bean.TokenBean;

import iot.handler.account.AccountHandler;
import iot.service.api.bean.AccountBean;
import iot.utils.exception.ErrorException;



public class AccountApiImpl extends ApiImpl{
	public ResponseBuilder createAccount(AccountBean bean) {
		try {
			bean.validate();
			body = new AccountHandler().createAccount(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder login(AccountBean bean) {
		try {
			bean.validate();
			body = new AccountHandler().login(bean);
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
	
	public ResponseBuilder getAccount(TokenBean bean) {
		try {
			bean.validate();
			body = new AccountHandler().getAccount(bean.getUserId());
			body.put("status", 200);
		} catch(ErrorException e) {
			body = e.getJSONObject();
		}
		return buildResponse();
	}
}
