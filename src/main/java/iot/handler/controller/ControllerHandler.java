package iot.handler.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;

import iot.db.model.Controller;
import iot.db.model.query.ControllerQuery;
import iot.db.model.update.ControllerUpdate;
import iot.service.api.bean.ControllerBean;
import iot.utils.exception.ArgumentErrorException;
import iot.utils.exception.BadRequestException;
import iot.utils.exception.NotFoundException;
import iot.utils.exception.UnauthorizedException;
import iot.utils.json.JSONConverter;
import iot.utils.mqtt.JobQueue;
import iot.utils.mqtt.MqttDataDecoder;


public class ControllerHandler {
	private ControllerUpdate update;
	private ControllerQuery query;
	private JobQueue queue;
	public ControllerHandler(){
		update = new ControllerUpdate();
		query= new ControllerQuery();
		queue = new JobQueue();
	}
	
	public JSONObject newController(ControllerBean bean) {
		Controller cr = update.create(bean);
		System.out.println("[createController] "+cr.toString());
		JSONObject body = cr.toJSONObject();
		return body;
	}

	public JSONObject controll(String userId, String controllerId, String mode, Long delay_time) {
		List<Controller> controllerList = query.findAll(userId);
		JSONObject body = new JSONObject();
		Boolean ControllerIsExists = false;
		Controller cr = null;
		for (Controller controller : controllerList){
			if(controller.getControllerId().equals(controllerId))
				ControllerIsExists =true;
				cr = controller;
		}
		if(!ControllerIsExists) throw new NotFoundException("Controller not found!");
		if(mode.equals("on") || mode.equals("off") || mode.equals("auto")){
			update.updateMode(cr, mode, delay_time);
		}else{
			throw new BadRequestException("wrong mode type!");
		}
		body.put("mode", mode);
		body.put("delay_time", cr.getDelaytime());
		return body;
	}
	
	public JSONObject getSensorList(String userId) {
		List<Controller> controllerList = query.findAll(userId);
		if(controllerList == null) throw new NotFoundException("ControllerList not found!");
		JSONObject body = new JSONObject();
		body.put("controllerList", controllerList);
		return body;
	}
	
	public JSONObject getControllerMode(String controllerId) {
		JSONObject body = new JSONObject();
		Controller cr = query.find(controllerId);
		if(cr==null) throw new NotFoundException("Controller not found!");
		body.put("mode", cr.getMode());
		body.put("delay_time", cr.getDelaytime());
		return body;
	}
	
	public JSONObject updateConfig(String userId, String controllerId, org.json.simple.JSONObject config) {
		JSONObject body = new JSONObject();
		Controller cr = query.find(controllerId);
		if(cr==null) throw new NotFoundException("Controller not found!");
		if(!cr.getOwnerId().equals(userId)) throw new UnauthorizedException("It's not ur controller!!!");
		cr = update.updateConfig(cr, config);
		body.put("config", cr.getConfig());
		return body;
	}
	
	public JSONObject getConfig(String userId, String controllerId) {
		JSONObject body = new JSONObject();
		Controller cr = query.find(controllerId);
		if(cr==null) throw new NotFoundException("Controller not found!");
		if(!cr.getOwnerId().equals(userId)) throw new UnauthorizedException("It's not ur controller!!!");
		body.put("config", cr.getConfig());
		return body;
	}
	
	public void autoControll(MqttMessage message) throws InterruptedException{
		MqttDataDecoder decoder = new MqttDataDecoder(message, 1);
		Controller cr = query.find("21001722-ce96-4ebc-a0f3-cbca69a7caa4");
		String irrigation_th = cr.getConfig().get("irrigation_threshold");
		String irrigation_ti = cr.getConfig().get("irrigation_time");
		Long irrigation_threshold, irrigation_time;
		if(irrigation_th.equals("undefined"))
			return;
		else if(irrigation_th.equals("default"))
			irrigation_threshold=20L;
		else{
			irrigation_threshold = Long.parseLong(irrigation_th);
		}
		if(irrigation_ti.equals("undefined"))
			return;
		else if(irrigation_ti.equals("default"))
			irrigation_time=300000L;
		else{
			irrigation_time = Long.parseLong(irrigation_ti);
		}
		if(decoder.getSoilHumidity()<irrigation_threshold){	
			update.updateMode(cr, "auto", irrigation_time);
			TimeUnit.SECONDS.sleep(5);
			update.updateMode(cr, "off", irrigation_time);
		}
	}
}
