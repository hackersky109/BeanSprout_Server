package iot.utils.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class MqttDataDecoder {
	private float soilTemperature, soilHumidity, airTemperature, airHumidity, realtimeWindSpeed, avgWindSpeed, highestWindSpeed;
	private int co2, light, voltage, windDirection, rainfallPerHour, rainfallPerDay;
	private long timestamp;
	
	public MqttDataDecoder(MqttMessage message, int type){
		JSONArray msg = new JSONArray(new String(message.getPayload()));
		String data = msg.getJSONObject(0).getString("data");
		String datetime = msg.getJSONObject(0).getString("time")+"Z";
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(datetime);
		this.timestamp = zonedDateTime.toInstant().toEpochMilli();
		switch(type){
			case 1:{
				this.soilTemperature=(float)Integer.parseInt(data.substring(6, 10),16)/100;
				this.soilHumidity=(float)Integer.parseInt(data.substring(10, 14),16)/100;
				this.co2=Integer.parseInt(data.substring(14, 18), 16);
				this.light=Integer.parseInt(data.substring(18, 22), 16);
				this.voltage=Integer.parseInt(data.substring(22, 26), 16);
				System.out.println("soilTemperature:"+this.soilTemperature
								  +"soilHumidity:"+this.soilHumidity
								  +"co2:"+this.co2
								  +"light:"+this.light
								  +"voltage"+this.voltage);
				break; 
			}
			case 2:{
				this.co2=Integer.parseInt(data.substring(1, 5));
				this.soilTemperature=new BigDecimal(data.substring(5, 8)).movePointLeft(1).floatValue();
				this.soilHumidity=new BigDecimal(data.substring(8, 11)).movePointLeft(1).floatValue();
				this.airTemperature=new BigDecimal(data.substring(11, 14)).movePointLeft(1).floatValue();
				this.airHumidity=new BigDecimal(data.substring(14, 17)).movePointLeft(1).floatValue();
				this.light=Integer.parseInt(data.substring(17, 22));
				System.out.println("soilTemperature:"+this.soilTemperature
						  +"soilHumidity:"+this.soilHumidity
						  +"co2:"+this.co2
						  +"light:"+this.light
						  +"airTemperature"+this.airTemperature
						  +"airHumidity"+this.airHumidity);
				break; 
			}
			case 3:{
				this.windDirection=Integer.parseInt(data.substring(1, 4));
				this.realtimeWindSpeed=new BigDecimal(data.substring(4, 7)).movePointLeft(1).floatValue();
				this.avgWindSpeed=new BigDecimal(data.substring(7, 10)).movePointLeft(1).floatValue();
				this.highestWindSpeed=new BigDecimal(data.substring(10, 13)).movePointLeft(1).floatValue();
				this.rainfallPerHour=Integer.parseInt(data.substring(13, 16));
				this.rainfallPerDay=Integer.parseInt(data.substring(16, 20));
				System.out.println("windDirection:"+this.windDirection
						  +"realtimeWindSpeed:"+this.realtimeWindSpeed
						  +"avgWindSpeed:"+this.avgWindSpeed
						  +"highestWindSpeed:"+this.highestWindSpeed
						  +"rainfallPerHour"+this.rainfallPerHour
						  +"rainfallPerDay"+this.rainfallPerDay);
				break; 
			}
		}

	}
	
	public float getSoilTemperature(){return this.soilTemperature;}

	public float getSoilHumidity(){return this.soilHumidity;}
	
	public int getCo2(){return this.co2;}
	
	public int getLight(){return this.light;}
	
	public int getVoltage(){return this.voltage;}	
	
	public int getWindDirection(){return this.windDirection;}
	
	public int getRainfallPerHour(){return this.rainfallPerHour;}
	
	public int getRainfallPerDay(){return this.rainfallPerDay;}
	
	public float getAirTemperature(){return this.airTemperature;}
	
	public float getAirHumidity(){return this.airHumidity;}
	
	public float getRealtimeWindSpeed(){return this.realtimeWindSpeed;}
	
	public float getAvgWindSpeed(){return this.avgWindSpeed;}
	
	public float getHighestWindSpeed(){return this.highestWindSpeed;}
	
	public long getTimestamp(){return this.timestamp;}
}
