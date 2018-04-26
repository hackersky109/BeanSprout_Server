package iot.utils.jwt;

import iot.utils.AbstractJSONObject;
import org.json.simple.JSONObject;

public class JsonWebToken extends AbstractJSONObject {
	private final JwtHeader header;
	private final JwtBody body;
	private final String jwt;
	
	public JsonWebToken(JwtHeader header, JwtBody body, String jwt) {
		this.header = header;
		this.body = body;
		this.jwt = jwt;
	}
	
	public String getSignature() {
		return jwt.substring(jwt.lastIndexOf(JwtConst.SEPARATOR)+1);
	}

	public String getJwt() {
		return jwt;
	}
	
	public String toString() {
		return "header: "+header.toString()+"body: "+body.toString();
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("jwt", jwt);
		return obj;
	}
	
	// Header Getters
	public JwtHeader getHeader() {
		return new JwtHeader(header.toJSONObject());
	}
	
	public String getTyp() {
		return header.getTyp();
	}
		
	public String getAlg() {
		return header.getAlg();
	}
	
	// Body Getters
	public JwtBody getBody() {
		return new JwtBody(body.toJSONObject());
	}
	
	public String getIss() {
		return body.getIss();
	}
	
	public String getAud() {
		return body.getAud();
	}
	
	public String getSub() {
		return body.getSub();
	}
	
	public long getIat() {
		return body.getIat();
	}
	
	public long getExp() {
		return body.getExp();
	}
}
