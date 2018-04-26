package iot.utils.mqtt;

import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.json.JSONArray;

import iot.handler.controller.ControllerHandler;
import iot.handler.data.MqttDataHandler;

public class Mqtt extends Thread {
	private static String preMacAddr;
	private static String preIdentifier;
	private static String preTimeIdentifierA;
	private static String preTimeIdentifierB;

    MqttClient mqtt;
    MqttCallback callback = new MqttCallback() {
    public void connectionLost(Throwable t){
        System.out.println("#####Connection lost!" + t);
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
    	
//	    	if(message.isDuplicate())
//	    		return;
	    	JSONArray msg = null;
	    	try {
	    		msg = new JSONArray(new String(message.getPayload()));
		} catch (Exception e) {
			return;
		}    	
	    	String macAddr = msg.getJSONObject(0).getString("macAddr").substring(8);
	    	String serverTime = new Timestamp(System.currentTimeMillis()).toString();
	    	String mqttTime = msg.getJSONObject(0).getString("time");
	    	System.out.println("macAddr:"+macAddr);
	    	if(macAddr.equals("05010380")||macAddr.equals("05010413")||macAddr.equals("05010409")||macAddr.equals("05010333")||macAddr.equals("05010312")){
	//        		if(macAddr.equals("05010380"))
	//        			new ControllerHandler().autoControll(message);
	    			String identifier = msg.getJSONObject(0).getString("data").substring(4, 6);
	    			if(!identifier.equals(preIdentifier)||!macAddr.equals(preMacAddr)) {
	    				new MqttDataHandler(message, 1).addData();
	    			}
	    		Mqtt.preIdentifier = identifier;
	    		Mqtt.preMacAddr = macAddr;
	    		
		    	System.out.println("Time:\t" +serverTime +
 					   "  Topic:\t" + topic +
 					   "  Message:\t" + new String(message.getPayload()) +
 					   "  QoS:\t" + message.getQos());
	    	}else if(macAddr.equals("0501097b")||macAddr.equals("05010735")){//AT+DTX=22
	    		if(msg.getJSONObject(0).getString("data").length()!=22||mqttTime.equals(preTimeIdentifierA))
	    			return;
	    		new MqttDataHandler(message, 2).addData();
	    		preTimeIdentifierA=mqttTime;
	    	}else if(macAddr.equals("05010811")||macAddr.equals("050106ed")){//AT+DTX=20
	    		if(msg.getJSONObject(0).getString("data").length()!=20||mqttTime.equals(preTimeIdentifierB))
	    			return;
	    		new MqttDataHandler(message, 3).addData();
	    		preTimeIdentifierB=mqttTime;
	    		}

    }

    public void deliveryComplete(IMqttDeliveryToken token) {
    }
   };
      
	public Mqtt(){
		
	}
	public void run() {
		connect();
	}
	public void connect(){
        try {
        		System.out.println("#####Connect to Mqttbroker!!!");
        	  	String tmpDir = System.getProperty("java.io.tmpdir");
        	  	MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        		mqtt = new MqttClient("tcp://104.199.215.165:1883",	"Rose", dataStore);
        		mqtt.setCallback(callback);
        		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        		mqttConnectOptions.setCleanSession(false);
        		mqttConnectOptions.setAutomaticReconnect(true);
        		mqtt.connect(mqttConnectOptions);
			mqtt.subscribe("GIOT-GW/UL/#", 2);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}