package iot.handler.data;

import java.util.List;

import iot.db.model.Data;
import iot.db.model.query.DataQuery;
import iot.db.model.update.DataUpdate;
import iot.service.api.bean.DataBean;

public class DataHandler {
	private DataQuery query;
	private DataUpdate update;

	public DataHandler(String tableName) {
		query = new DataQuery(tableName);
		update = new DataUpdate(tableName);
		}
	public List<Data> newData(DataBean bean, int value) {
		return update.newData(bean, value);
	}
		
	public List<Data> getData(String sensorId, String from, String to, int limit) {
		if(from!=null||to!=null)
			return query.getDataWithinTimePeriod("sensorId",sensorId, from, to, limit);
		else 
			return query.getAllData("sensorId",sensorId);
	}
	
	public List<Data> deleteData(List<Data> dataList) {
		return update.deleteData(dataList);
	}
			
}
