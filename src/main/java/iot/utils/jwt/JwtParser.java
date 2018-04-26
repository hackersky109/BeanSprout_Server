package iot.utils.jwt;

import iot.utils.crypt.BASE64;
import org.json.simple.JSONObject;

public class JwtParser {
	
	public JsonWebToken parse(String jwt) {
		String[] jwtParts = jwt.split("\\"+JwtConst.SEPARATOR);
		JwtHeader header = parseHeader(jwtParts[0]);
		JwtBody body = parseBody(jwtParts[1]);
		return new JsonWebToken(header, body, jwt);
	}
	
	public JwtBody parseBody(String base64JwtBody) {
		JSONObject decodedBody = BASE64.decodeUrlToJSONObject(base64JwtBody);
		JwtBody body = new JwtBody(decodedBody);
		return body;
	}
	
	public JwtHeader parseHeader(String base64JwtHeader) {
		JSONObject decodedHeader = BASE64.decodeUrlToJSONObject(base64JwtHeader);
		JwtHeader header = new JwtHeader(decodedHeader);
		return header;
	}
}
