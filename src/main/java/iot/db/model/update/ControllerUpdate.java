package iot.db.model.update;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import iot.db.helper.dynamodb.update.ControllerUpdateDynamoHelper;
import iot.db.model.Controller;
import iot.service.api.bean.ControllerBean;
import iot.utils.exception.ArgumentErrorException;

public class ControllerUpdate {
	private ControllerUpdateDynamoHelper updateHelper;
	
	public ControllerUpdate() {
		updateHelper = new ControllerUpdateDynamoHelper("Controller");
	}
	
	public Controller create(ControllerBean bean) {
//		if(!bean.isValid()) 
//			throw new BadRequestException("create sensor failed due to empty fields or wrong sensertype");
		Controller cr = new Controller(bean);
		cr.setOwnerId(bean.getUserId());
		updateHelper.saveItem(cr);
		return cr;
	}
	
	public Controller updateMode(Controller cr, String mode, Long delay_time) {
		cr.setMode(mode);
		if(mode.equals("auto")){
			cr.setDelaytime(delay_time);
			updateHelper.saveItem(cr);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cr.setMode("off");
			updateHelper.saveItem(cr);
		}else{
			updateHelper.saveItem(cr);
		}
		return cr;
	}
	
	public Controller updateConfig(Controller cr, org.json.simple.JSONObject config) {
		Map cn = cr.getConfig();
		if(config.get("irrigation_threshold")!=null||config.get("irrigation_time")!=null){
			if(config.get("irrigation_threshold")!=null)
				cn.put("irrigation_threshold", config.get("irrigation_threshold"));
			if(config.get("irrigation_time")!=null)
				cn.put("irrigation_time", config.get("irrigation_time"));
		}else throw new ArgumentErrorException("wrong config arg!!!");
		checkConfigValue(cn);
		cr.setConfig(cn);
		updateHelper.saveItem(cr);		
		return cr;
	}
	public Boolean checkConfigValue(Map cn){
		Boolean thresholdValid = false;
		Boolean timeValid = false;
		String irrigation_threshold = cn.get("irrigation_threshold").toString();
		String irrigation_time = cn.get("irrigation_time").toString();
		try {
			if(irrigation_threshold.equals("undefined")||irrigation_threshold.equals("default"))
				thresholdValid = true;
			else{
				Long.parseLong(irrigation_threshold);
				thresholdValid = true;
			}
			if(irrigation_time.equals("undefined")||irrigation_time.equals("default"))
				timeValid = true;
			else{
				Long.parseLong(irrigation_time);
				timeValid = true;
			}	
		} catch (Exception e) {
			throw new ArgumentErrorException("wrong config value!!!");
		}
		return thresholdValid&&timeValid;
	}
	

}
