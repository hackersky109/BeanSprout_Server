package iot.handler.data;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import iot.db.model.Data;
import iot.db.model.Sensor;
import iot.db.model.SensorGroup;
import iot.db.model.query.SensorGroupQuery;
import iot.db.model.query.SensorQuery;
import iot.db.model.update.SensorGroupUpdate;
import iot.service.api.bean.DataBean;
import iot.utils.Timestamp;
import iot.utils.exception.NotFoundException;
import iot.utils.mqtt.MqttDataDecoder;

public class MqttDataHandler {
	private SensorGroupUpdate update;
	private SensorGroupQuery query;
	private SensorQuery sensorquery;
	MqttDataDecoder decoder;
	String macAddr;
	private JSONArray dataList;
	
	public MqttDataHandler(MqttMessage message, int type){
		update = new SensorGroupUpdate();
		query= new SensorGroupQuery();		
		sensorquery = new SensorQuery();
		decoder = new MqttDataDecoder(message, type);
		macAddr = new JSONArray(new String(message.getPayload())).getJSONObject(0).getString("macAddr").substring(8);
	}
	
	public JSONObject addData(){
		String sensorGroupId = null;
		long timestamp = decoder.getTimestamp();
		long severTimestamp = Timestamp.now();
		if(macAddr.equals("05010380")){
			sensorGroupId = "e1206788-95d1-45bd-b1be-9b6845dbeefb";
			JSONObject obj1 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", timestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getLight()).put("timestamp", timestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", timestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getVoltage()).put("timestamp", timestamp); 
			JSONObject obj5 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", timestamp);
			dataList=new JSONArray().put(new JSONObject().put("df02832c-7a21-4e55-ae1e-2bea72731072", new JSONArray().put(obj1)))
			.put(new JSONObject().put("5407f3fb-9710-43e9-96f4-821cde80c62e", new JSONArray().put(obj2)))
			.put(new JSONObject().put("c1c5435a-7679-4ce9-8f00-2ab990aa5a97", new JSONArray().put(obj3)))
			.put(new JSONObject().put("d9ad7870-a15d-4355-ab1d-3b83245ba9f9", new JSONArray().put(obj4)))
			.put(new JSONObject().put("f2342c11-dbf5-4b83-b290-74b381338668", new JSONArray().put(obj5)));
		}
		else if(macAddr.equals("05010413")){
			sensorGroupId = "49027b27-e91b-48c3-9c72-71e1d1829dd9";
			JSONObject obj1 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", timestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getLight()).put("timestamp", timestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", timestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getVoltage()).put("timestamp", timestamp); 
			JSONObject obj5 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", timestamp);
			dataList=new JSONArray().put(new JSONObject().put("b79ef484-82e9-48af-935c-29d55a13749a", new JSONArray().put(obj1)))
			.put(new JSONObject().put("a88b8342-69f9-48e3-88af-01d3cb46f9ea", new JSONArray().put(obj2)))
			.put(new JSONObject().put("cddde0c0-1b83-41b0-ae41-1901eccfb4b3", new JSONArray().put(obj3)))
			.put(new JSONObject().put("79c0b5b5-a102-4f4f-a600-4f1b45d8fd73", new JSONArray().put(obj4)))
			.put(new JSONObject().put("11bf115e-f322-494e-93a8-623f9ee227d1", new JSONArray().put(obj5)));
		}
		else if(macAddr.equals("05010333")){
			sensorGroupId = "d49fbb3b-5874-4a88-98c4-f95ddc684be5";
			JSONObject obj1 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", timestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getLight()).put("timestamp", timestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", timestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getVoltage()).put("timestamp", timestamp); 
			JSONObject obj5 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", timestamp);
			dataList=new JSONArray().put(new JSONObject().put("cbe3e02d-d2f1-46a4-9e76-722706210dba", new JSONArray().put(obj1)))
			.put(new JSONObject().put("57c1931e-7da2-492f-9574-a39be7ca62b4", new JSONArray().put(obj2)))
			.put(new JSONObject().put("661ba294-febd-4b9a-b61b-96d4af2fe076", new JSONArray().put(obj3)))
			.put(new JSONObject().put("39f3ff2f-53fd-499d-93db-fcc71020f97d", new JSONArray().put(obj4)))
			.put(new JSONObject().put("18703831-a9bd-47b3-bbde-106bf47797a3", new JSONArray().put(obj5)));
		}
		else if(macAddr.equals("05010312")){
			sensorGroupId = "476b219b-0d96-4e9b-86f1-c86e14fbba4e";
			JSONObject obj1 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", timestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getLight()).put("timestamp", timestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", timestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getVoltage()).put("timestamp", timestamp); 
			JSONObject obj5 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", timestamp);
			dataList=new JSONArray().put(new JSONObject().put("d2068e8b-909c-4bfb-9e9a-d4d471f4ce2d", new JSONArray().put(obj1)))
			.put(new JSONObject().put("154a0d34-43dc-440c-bc99-f503330e7962", new JSONArray().put(obj2)))
			.put(new JSONObject().put("2ade1a9a-fe95-4de0-aa39-101b7a33f414", new JSONArray().put(obj3)))
			.put(new JSONObject().put("988c7d37-ba19-47ec-b25a-e47c62e2924d", new JSONArray().put(obj4)))
			.put(new JSONObject().put("48857dd5-1ac0-4f24-97f4-45eae17c4574", new JSONArray().put(obj5)));
		}
		else if(macAddr.equals("05010409")){//三星旭和
			sensorGroupId = "43ce239c-b5db-427b-823a-d88bbe0f985a";
			JSONObject obj1 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", severTimestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getLight()).put("timestamp", severTimestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", severTimestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getVoltage()).put("timestamp", severTimestamp); 
			JSONObject obj5 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", severTimestamp);
			dataList=new JSONArray().put(new JSONObject().put("c99dff4b-371a-4017-9e1f-2ad0dea7d8c9", new JSONArray().put(obj1)))
			.put(new JSONObject().put("456cb5e4-a582-47ef-b8e9-aa15b2204eb6", new JSONArray().put(obj2)))
			.put(new JSONObject().put("61a141eb-7336-4846-aecb-874d0fc98177", new JSONArray().put(obj3)))
			.put(new JSONObject().put("aeb7b54f-200b-4b1c-aded-76fac57e4115", new JSONArray().put(obj4)))
			.put(new JSONObject().put("59258fd0-0fdb-49c7-983c-fd8e3a8c77e2", new JSONArray().put(obj5)));
		}
		else if(macAddr.equals("0501097b")){//a
			sensorGroupId = "372308af-eb1c-429b-b286-b631a0b7b038";
			JSONObject obj1 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", severTimestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", severTimestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", severTimestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getAirTemperature()).put("timestamp", severTimestamp);
			JSONObject obj5 = new JSONObject().put("value", decoder.getAirHumidity()).put("timestamp", severTimestamp);
			JSONObject obj6 = new JSONObject().put("value", decoder.getLight()).put("timestamp", severTimestamp);
			dataList=new JSONArray().put(new JSONObject().put("308937c1-0e54-4ebe-aa39-d0d8e9f2efca", new JSONArray().put(obj1)))
			.put(new JSONObject().put("6f96ad55-a817-4177-b87e-2952d7dd621e", new JSONArray().put(obj2)))
			.put(new JSONObject().put("82f93e0c-727f-4ed5-a24e-a5fbb16ad4ee", new JSONArray().put(obj3)))
			.put(new JSONObject().put("9a41633e-a4a5-4efa-953e-bc54cbcf54dc", new JSONArray().put(obj4)))
			.put(new JSONObject().put("81cfd36d-e04b-43a2-9de0-47c05d5863e2", new JSONArray().put(obj5)))
			.put(new JSONObject().put("bcc820f2-af91-4e77-9cfd-58a53480af96", new JSONArray().put(obj6)));
		}else if(macAddr.equals("05010811")){//b
			sensorGroupId = "5ed417a0-dba1-42fd-9fad-c52ff105aa6d";
			JSONObject obj1 = new JSONObject().put("value", decoder.getWindDirection()).put("timestamp", severTimestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getRealtimeWindSpeed()).put("timestamp", severTimestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getAvgWindSpeed()).put("timestamp", severTimestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getHighestWindSpeed()).put("timestamp", severTimestamp);
			JSONObject obj5 = new JSONObject().put("value", decoder.getRainfallPerHour()).put("timestamp", severTimestamp);
			JSONObject obj6 = new JSONObject().put("value", decoder.getRainfallPerDay()).put("timestamp", severTimestamp);
			dataList=new JSONArray().put(new JSONObject().put("e14b97f4-d503-4367-bc05-1c88cd6a32e5", new JSONArray().put(obj1)))
			.put(new JSONObject().put("954a91b3-011d-4b88-82ff-96f1bd0e1a1f", new JSONArray().put(obj2)))
			.put(new JSONObject().put("a67731e2-d5bd-4079-a21f-cf9d10dbd1ce", new JSONArray().put(obj3)))
			.put(new JSONObject().put("3eb20ade-70a3-4a1b-96de-4ada175e0d74", new JSONArray().put(obj4)))
			.put(new JSONObject().put("09b9f095-99f4-4b37-bdde-272b4b66a762", new JSONArray().put(obj5)))
			.put(new JSONObject().put("9d110675-7930-4b88-9389-88844d0e1ec3", new JSONArray().put(obj6)));
		}else if(macAddr.equals("05010735")){//c
			sensorGroupId = "858b7554-50b4-4ed1-8d83-518ee9fd9929";
			JSONObject obj1 = new JSONObject().put("value", decoder.getCo2()).put("timestamp", severTimestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getSoilTemperature()).put("timestamp", severTimestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getSoilHumidity()).put("timestamp", severTimestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getAirTemperature()).put("timestamp", severTimestamp);
			JSONObject obj5 = new JSONObject().put("value", decoder.getAirHumidity()).put("timestamp", severTimestamp);
			JSONObject obj6 = new JSONObject().put("value", decoder.getLight()).put("timestamp", severTimestamp);
			dataList=new JSONArray().put(new JSONObject().put("659338ef-fffe-42a4-84b4-a65d6f4ba613", new JSONArray().put(obj1)))
			.put(new JSONObject().put("f3cadcbf-e816-4aed-b403-0a2fa911e7bb", new JSONArray().put(obj2)))
			.put(new JSONObject().put("ff3b13f8-c389-4e48-bb7a-d8504d944585", new JSONArray().put(obj3)))
			.put(new JSONObject().put("edaf2693-c370-4686-9d2a-b3af901bfc16", new JSONArray().put(obj4)))
			.put(new JSONObject().put("d796dae0-6a69-4b39-8e37-76db8584cf48", new JSONArray().put(obj5)))
			.put(new JSONObject().put("060da1c3-1a4c-4cce-8728-428e28dbc667", new JSONArray().put(obj6)));
		}else if(macAddr.equals("050106ed")){//d
			sensorGroupId = "57dc3d38-fb90-4ecc-9af1-9f6168697395";
			JSONObject obj1 = new JSONObject().put("value", decoder.getWindDirection()).put("timestamp", severTimestamp);
			JSONObject obj2 = new JSONObject().put("value", decoder.getRealtimeWindSpeed()).put("timestamp", severTimestamp);
			JSONObject obj3 = new JSONObject().put("value", decoder.getAvgWindSpeed()).put("timestamp", severTimestamp);
			JSONObject obj4 = new JSONObject().put("value", decoder.getHighestWindSpeed()).put("timestamp", severTimestamp);
			JSONObject obj5 = new JSONObject().put("value", decoder.getRainfallPerHour()).put("timestamp", severTimestamp);
			JSONObject obj6 = new JSONObject().put("value", decoder.getRainfallPerDay()).put("timestamp", severTimestamp);
			dataList=new JSONArray().put(new JSONObject().put("17a8ed13-bf63-453b-8aa3-fb054c593077", new JSONArray().put(obj1)))
			.put(new JSONObject().put("31a80eab-5ca4-4c1c-9cc8-dfb30cf820ad", new JSONArray().put(obj2)))
			.put(new JSONObject().put("ddaa9f4a-3541-4530-9d21-76de9186e2de", new JSONArray().put(obj3)))
			.put(new JSONObject().put("9b8aa013-d5d8-45a3-b00c-2453a0be7d33", new JSONArray().put(obj4)))
			.put(new JSONObject().put("5d4929b5-5dba-4f92-bafc-86c04ecbf1e2", new JSONArray().put(obj5)))
			.put(new JSONObject().put("48230f80-4f44-46c5-9837-36995be550ec", new JSONArray().put(obj6)));
		}
		System.out.println(dataList.toString());
		SensorGroup sg = query.find(sensorGroupId);
		if(sg == null) throw new NotFoundException("SensorGroup not found!");
		JSONObject result = new JSONObject();

		for(int i=0; i<dataList.length(); i++){
			Sensor ss = sensorquery.find(dataList.getJSONObject(i).names().getString(0));	
			DataBean db = new DataBean(dataList.getJSONObject(i).getJSONArray(ss.getSensorId()), ss);
			List<Data> dataList = new DataHandler(db.getSensorType()).newData(db);
			result.put(ss.getSensorId(), dataList);
		}
		return result;
	}
}
