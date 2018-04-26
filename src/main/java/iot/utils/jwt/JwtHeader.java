package iot.utils.jwt;

import iot.utils.AbstractJSONObject;
import org.json.simple.JSONObject;

public class JwtHeader extends AbstractJSONObject {
	private String typ;
	private String alg;
	
	public JwtHeader() {
		this.typ = JwtConst.DEFAULT_TYP;
		this.alg = JwtConst.DEFAULT_ALG;
	}
	
	public JwtHeader(JSONObject obj) {
		this.typ = (String) obj.get(JwtConst.TYP);
		this.alg = (String) obj.get(JwtConst.ALG);
	}
	
	// Setters
	public JwtHeader setTyp(String typ) {
		this.typ = JwtConst.TYP;
		return this;
	}
	
	public JwtHeader setAlg(String alg) {
		this.alg = JwtConst.ALG;
		return this;
	}
	
	// Getters
	public String getTyp() {
		return typ;
	}
	
	public String getAlg() {
		return alg;
	}
}
