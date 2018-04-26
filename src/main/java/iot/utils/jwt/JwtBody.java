package iot.utils.jwt;

import iot.utils.AbstractJSONObject;
import iot.utils.Timestamp;
import org.json.simple.JSONObject;

public class JwtBody extends AbstractJSONObject {
	// Issuer
	private String iss;
	// Subject
	private String sub;
	// Audience
	private String aud;
	// Issue at
	private long iat;
	// Expiration Time
	private long exp;
	
	public JwtBody(String aud) {
		this.iss = JwtConst.DEFAULT_ISS;
		this.sub = JwtConst.DEFAULT_SUB;
		this.iat = Timestamp.now();
		this.exp = iat + JwtConst.DEFAULT_EXP_DURATION;
		this.aud = aud;
	}
	
	public JwtBody(JSONObject obj) {
		this.iss = (String) obj.get(JwtConst.ISS);
		this.sub = (String) obj.get(JwtConst.SUB);
		this.aud = (String) obj.get(JwtConst.AUD);
		this.iat = (long) obj.get(JwtConst.IAT);
		this.exp = (long) obj.get(JwtConst.EXP);
	}
	
	// Setters
	public JwtBody setIss(String iss) {
		this.iss = iss;
		return this;
	}
	
	public JwtBody setAud(String aud) {
		this.aud = aud;
		return this;
	}
	
	public JwtBody setSub(String sub) {
		this.sub = sub;
		return this;
	}
	
	public JwtBody setIat(long iat) {
		this.iat = iat;
		return this;
	}
	
	public JwtBody setExp(long exp) {
		this.exp = exp;
		return this;
	}
	
	// Getters
	public String getIss() {
		return iss;
	}
	
	public String getAud() {
		return aud;
	}
	
	public String getSub() {
		return sub;
	}
	
	public long getIat() {
		return iat;
	}
	
	public long getExp() {
		return exp;
	}
}
