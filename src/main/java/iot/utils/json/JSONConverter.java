package iot.utils.json;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONConverter {
	
	public static org.json.simple.JSONArray toSimpleJSONArray(org.json.JSONArray jarr) {
		try {
			return (org.json.simple.JSONArray) new JSONParser().parse(jarr.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("[JSONConverter]: toSimpleJSONArray parse error");
			return null;
		}
	}
	
	public static org.json.simple.JSONObject toSimpleJSONObject(org.json.JSONObject jobj) {
		try {
			return (org.json.simple.JSONObject) new JSONParser().parse(jobj.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("[JSONConverter]: toSimpleJSONObject parse error");
			return null;
		}
	}
	
}
