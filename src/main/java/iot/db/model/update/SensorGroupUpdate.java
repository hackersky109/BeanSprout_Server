package iot.db.model.update;

import java.util.ArrayList;
import java.util.List;

import iot.db.helper.dynamodb.update.SensorGroupUpdateDynamoHelper;
import iot.db.model.Sensor;
import iot.db.model.SensorGroup;
import iot.db.model.query.SensorQuery;
import iot.service.api.bean.SensorGroupBean;
import iot.utils.exception.BadRequestException;

public class SensorGroupUpdate {
	private SensorQuery sensorquery;
	private SensorGroupUpdateDynamoHelper updateHelper;
	
	public SensorGroupUpdate() {
		updateHelper = new SensorGroupUpdateDynamoHelper("SensorGroup");
		sensorquery= new SensorQuery();
	}
	
	public SensorGroup create(SensorGroupBean bean) {
		if(!bean.isValid()) 
			throw new BadRequestException("create sensor group failed due to empty fields");
		List<Sensor> sensorList = new ArrayList<>();
		for(int i=0; i<bean.getSensorList().length(); i++){
			String sensorId = bean.getSensorList().getJSONObject(i).getString("sensorId");
			sensorList.add(sensorquery.find(sensorId));
		}
		SensorGroup sg = new SensorGroup(bean,sensorList);
		updateHelper.saveItem(sg);
		return sg;
	}
}
