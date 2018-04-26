package iot.handler.sensor;

import java.io.IOException;
import java.util.List;


import org.json.simple.JSONObject;

import iot.db.model.Account;
import iot.db.model.Data;
import iot.db.model.Record;
import iot.db.model.Sensor;
import iot.db.model.query.AccountQuery;
import iot.db.model.query.RecordQuery;
import iot.db.model.query.SensorQuery;
import iot.db.model.update.RecordUpdate;
import iot.db.model.update.SensorUpdate;
import iot.handler.data.DataHandler;
import iot.handler.record.RecordHandler;
import iot.service.api.bean.DataBean;
import iot.service.api.bean.SensorBean;
import iot.service.api.bean.TokenBean;
import iot.utils.Timestamp;
import iot.utils.exception.NotFoundException;
import iot.utils.exception.UnauthorizedException;
import iot.utils.fcm.PushNotifictionHelper;

public class SensorHandler {
	private SensorUpdate update;
	private SensorQuery query;
	
	public SensorHandler(){
		update = new SensorUpdate();
		query= new SensorQuery();
	}
	
	public JSONObject newSensor(SensorBean bean) {
		Sensor ss = update.create(bean);
		System.out.println("[createSensor] "+ss.toJSONObject().toJSONString());
		JSONObject body = ss.toJSONObject();
		return body;
	}
	
	public JSONObject getSensor(String sensorId) {
		Sensor ss = query.find(sensorId);
		if(ss == null) throw new NotFoundException("Sensor not found!");
		return ss.toJSONObject();
	}
	
	public JSONObject getSensorList(String userId) {
		List<Sensor> sensorList = query.findAll(userId);
		if(sensorList == null) throw new NotFoundException("SensorList not found!");
		JSONObject result = new JSONObject();
		result.put("sensorList", sensorList);
		return result;
	}

	public JSONObject addData(DataBean bean) {
		Sensor ss = query.find(bean.getSensorId());
		if(ss == null) throw new NotFoundException("Sensor not found!");
		if(!ss.getUploadKey().equals(bean.getUploadKey())) throw new UnauthorizedException("Invalid uploadKey!");
		bean.setUserId(ss.getOwnerId()).setSensorType(ss.getSensorType());
		List<Data> dataList = new DataHandler(bean.getSensorType()).newData(bean);
		
		//For BeanSprout
		if(bean.getSensorType().equals(SensorType.BEANSPROUT_DISTANCE.toString())) {
			RecordQuery recordq = new RecordQuery();
			List<Record> recordList = recordq.findAllBySid(ss.getSensorId());
			Record record_InProgress = null;
			for(int i=0;i<recordList.size();i++) {
				if(recordList.get(i).getInProgress())
					record_InProgress = recordList.get(i);
			}
			if(record_InProgress!=null) {
				Double distance_now = dataList.get(0).getValue();
				int percentage = (int)(((record_InProgress.getInitialValue()-distance_now)/record_InProgress.getTargetValue())*100);
				record_InProgress.setPercentage(percentage);
				if(percentage>=100) {
					record_InProgress.setInProgress(false);
					record_InProgress.setEndTime(Timestamp.now());
					Account acc = new AccountQuery().find(ss.getOwnerId());
					try {
						PushNotifictionHelper.sendPushNotification(acc.getFcmToken(), ss.getSensorName(), "已經生長完成囉!!!");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				RecordUpdate recordu = new RecordUpdate();
				recordu.updateRecord(record_InProgress);
			}
				
		}
		//For BeanSprout
		
		JSONObject result = new JSONObject();	
		result.put("dataList", dataList);
		return result;
	}
	
	public JSONObject getData(String sensorId, String from, String to) {
		Sensor ss = query.find(sensorId);
		if(ss == null) throw new NotFoundException("Sensor not found!");
		List<Data> dataList = new DataHandler(ss.getSensorType()).getData(sensorId, from, to, 0);
		if(dataList.isEmpty()) throw new NotFoundException("Data not found!");
		JSONObject result = new JSONObject();	
		result.put("dataList", dataList);
		System.out.println(result);
		return result;
	}
	
	public JSONObject deleteSensor(TokenBean bean, String sensorId) {
		Sensor ss = query.find(sensorId);
		if(!ss.getOwnerId().equals(bean.getUserId()))
			throw new UnauthorizedException("Invalid Operation!");
		update.delete(ss);
		DataHandler DH = new DataHandler(ss.getSensorType());
		List<Data> dataList = null;
		try {
			dataList = DH.getData(sensorId, null, null, 0); 
		}catch (Exception e) {
			// TODO: handle exception
		}
		if(dataList!=null)	
			DH.deleteData(dataList);
		if(ss.getSensorType().equals(SensorType.BEANSPROUT_DISTANCE.toString()))
			 new RecordHandler().deleteAllRecord(sensorId);
		System.out.println("[deleteSensor] "+ss.toJSONObject().toJSONString());
		JSONObject body = ss.toJSONObject();
		return body;
	}
}
