package iot.handler.record;

import java.util.List;


import org.json.simple.JSONObject;

import iot.db.model.Data;
import iot.db.model.Record;
import iot.db.model.query.DataQuery;
import iot.db.model.query.RecordQuery;
import iot.db.model.update.RecordUpdate;
import iot.handler.data.DataHandler;
import iot.handler.sensor.SensorType;
import iot.service.api.bean.RecordBean;
import iot.utils.Timestamp;
import iot.utils.exception.NotFoundException;

public class RecordHandler {
	private RecordUpdate update;
	private RecordQuery query;
	
	public RecordHandler(){
		update = new RecordUpdate();
		query= new RecordQuery();
	}
	
	public JSONObject newRecord(RecordBean bean) {
		List<Data> d = new DataHandler(SensorType.BEANSPROUT_DISTANCE.toString()).getData(bean.getSensorId(), null, Long.toString(Timestamp.now()), 1);
		Data data_now = null;
		try {
			data_now = d.get(0);
		} catch (Exception e) {
			throw new NotFoundException("SensorData not found!");		
		}
		Record rd = update.create(bean, data_now.getValue());
		System.out.println("[createSensor] "+rd.toJSONObject().toJSONString());
		JSONObject body = rd.toJSONObject();
		return body;
	}
	
	public JSONObject getRecord(String recordId) {
		Record rd = query.find(recordId);
		if(rd == null) throw new NotFoundException("Record not found!");
		return rd.toJSONObject();
	}
	
	public JSONObject getRecordList(String sensorId) {
		List<Record> recordList = query.findAllBySid(sensorId);
		if(recordList == null) throw new NotFoundException("RecordList not found!");
		JSONObject result = new JSONObject();
		result.put("recordList", recordList);
		return result;
	}
	
	public JSONObject deleteRecord(String recordId) {
		Record rd = query.findAllByRid(recordId).get(0);
		if(rd == null) throw new NotFoundException("Record not found!");
		update.deleteRecord(rd);
		return rd.toJSONObject();
	}
	
	public void deleteAllRecord(String sensorId) {
		List<Record> recordList = query.findAllBySid(sensorId);
		if(recordList == null) throw new NotFoundException("Record not found!");
		for(int i=0;i<recordList.size();i++)
			update.deleteRecord(recordList.get(i));
	}
}
