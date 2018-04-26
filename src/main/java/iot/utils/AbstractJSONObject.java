package iot.utils;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;

public abstract class AbstractJSONObject {
	public AbstractJSONObject() {
		
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		for(Field field : this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object value = field.get(this);
				if(value != null)
					obj.put(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	public String toString() {
		return toJSONObject().toJSONString();
	}
}
