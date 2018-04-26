package iot.db.model.query;

import java.util.List;

import iot.db.helper.dynamodb.query.ControllerQueryDynamoHelper;
import iot.db.model.Controller;

public class ControllerQuery {
	private ControllerQueryDynamoHelper queryHelper;
	public ControllerQuery() {
		queryHelper = new ControllerQueryDynamoHelper("Controller");
	}
	
	public Controller find(String controllerId) {
		return queryHelper.loadByPk(controllerId);
	}
	
	public List<Controller> findAll(String userId) {
		return queryHelper.scanAttributeN("ownerId", userId);
	}
}
