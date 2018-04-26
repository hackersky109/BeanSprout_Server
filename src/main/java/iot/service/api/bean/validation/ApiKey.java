package iot.service.api.bean.validation;
import iot.utils.PropertiesLoader;

public class ApiKey {
	public static final String KEY = PropertiesLoader.getProperty("iot.api.key");
	
	public static boolean isValid(String apiKey) {
		return KEY.equals(apiKey);
	}
}
