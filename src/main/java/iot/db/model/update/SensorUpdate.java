package iot.db.model.update;


import iot.db.helper.dynamodb.update.SensorUpdateDynamoHelper;
import iot.db.model.Sensor;
import iot.service.api.bean.SensorBean;
import iot.utils.exception.BadRequestException;

public class SensorUpdate {
	private SensorUpdateDynamoHelper updateHelper;
	
	public SensorUpdate() {
		updateHelper = new SensorUpdateDynamoHelper("Bean_Sensor");
	}
	
	public Sensor create(SensorBean bean) {
		if(!bean.isValid()) 
			throw new BadRequestException("create sensor failed due to empty fields or wrong sensertype");
		Sensor ss = new Sensor(bean);
		ss.setOwnerId(bean.getUserId());
		updateHelper.saveItem(ss);
		return ss;
	}
	
	public Sensor delete(Sensor s) {
		updateHelper.deleteItem(s);
		return s;
	}

}
