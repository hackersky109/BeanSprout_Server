package iot.utils.jwt;

import iot.utils.crypt.BASE64;

public class JwtBuilder {
	private JwtHeader header;
	private JwtBody body;
	
	public JwtBuilder(String aud) {
		this.header = new JwtHeader();
		this.body = new JwtBody(aud);
	}
	
	public JwtBuilder(JwtHeader header, JwtBody body) {
		this.header = header;
		this.body = body;
	}
	
	public String jwtSign() {
		return new JwtSigner().sign(baseJwt());
	}
	
	private String baseJwt() {
		return BASE64.encodeUrl(header.toString())+JwtConst.SEPARATOR+
			   BASE64.encodeUrl(body.toString());
	}
	
	public JsonWebToken build() {
		String jwt = baseJwt()+JwtConst.SEPARATOR+jwtSign();
		return new JsonWebToken(header, body, jwt);
	}

	// Setters
	public JwtBuilder setHeader(JwtHeader header) {
		this.header = header;
		return this;
	}
	
	public JwtBuilder setBody(JwtBody body) {
		this.body = body;
		return this;
	}
	
	// Getters
	public JwtHeader getHeader() {
		return this.header;
	}
	
	public JwtBody getBody() {
		return this.body;
	}
}
