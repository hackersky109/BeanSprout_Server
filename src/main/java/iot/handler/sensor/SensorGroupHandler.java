package iot.handler.sensor;

import java.util.List;

import org.json.simple.JSONObject;

import iot.db.model.Data;
import iot.db.model.Sensor;
import iot.db.model.SensorGroup;
import iot.db.model.query.SensorGroupQuery;
import iot.db.model.query.SensorQuery;
import iot.db.model.update.SensorGroupUpdate;
import iot.handler.data.DataHandler;
import iot.service.api.bean.DataBean;
import iot.service.api.bean.GroupDataBean;
import iot.service.api.bean.SensorGroupBean;
import iot.utils.exception.NotFoundException;
import iot.utils.exception.UnauthorizedException;


public class SensorGroupHandler {
	private SensorGroupUpdate update;
	private SensorGroupQuery query;
	private SensorQuery sensorquery;
	
	public SensorGroupHandler(){
		update = new SensorGroupUpdate();
		query= new SensorGroupQuery();		
		sensorquery = new SensorQuery();
	}
	public JSONObject newSensorGroup(SensorGroupBean bean) {
		SensorGroup sg = update.create(bean);
		System.out.println("[createSensorGroup] "+sg.toJSONObject().toJSONString());
		JSONObject body = sg.toJSONObject();
		return body;
	}

	public JSONObject addData(GroupDataBean bean) {
		SensorGroup sg = query.find(bean.getSensorGroupId());
		if(sg == null) throw new NotFoundException("SensorGroup not found!");
		if(!sg.getUploadKey().equals(bean.getUploadKey())) throw new UnauthorizedException("Invalid uploadKey!");		
		JSONObject result = new JSONObject();
		for(int i=0; i<bean.getDataList().length(); i++){
			Sensor ss = sensorquery.find(bean.getDataList().getJSONObject(i).names().getString(0));	
			DataBean db = new DataBean(bean.getDataList().getJSONObject(i).getJSONArray(ss.getSensorId()), ss);
			List<Data> dataList = new DataHandler(db.getSensorType()).newData(db);
			result.put(ss.getSensorId(), dataList);
		}
		return result;
	}
}
