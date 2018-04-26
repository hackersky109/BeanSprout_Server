package iot.db.model.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;

import iot.db.helper.dynamodb.update.DataUpdateDynamoHelper;
import iot.db.model.Data;
import iot.service.api.bean.DataBean;


public class DataUpdate {
	private DataUpdateDynamoHelper updateHelper;
	private TableNameOverride configoveride;
	private DynamoDBMapperConfig config;
	public DataUpdate(String tableName){
		configoveride =  new DynamoDBMapperConfig.TableNameOverride(tableName);
		config = new DynamoDBMapperConfig(configoveride);
		updateHelper = new DataUpdateDynamoHelper(tableName, config);	
	}

	public List<Data> newData(DataBean bean) {
		JSONArray data = bean.getData();
		List<Data> dataList = new ArrayList<Data>();
		for(int i=0;i<data.length();i++){
			JSONObject obj = data.getJSONObject(i);
			Data newdata = new Data(bean);
			newdata.setValue(obj.getDouble("value"));
			newdata.setTimestamp(obj.getLong("timestamp"));
			dataList.add(newdata);
		}	
		List<Data> objToDelete = Collections.<Data> emptyList();
		updateHelper.batchWrite(dataList, objToDelete, config);
		
		return dataList;
	}
	
	public List<Data> deleteData(List<Data> dataList) {
			
		List<Data> objToAdd = Collections.<Data> emptyList();
		updateHelper.batchWrite(objToAdd, dataList, config);
		return dataList;
	}

}
