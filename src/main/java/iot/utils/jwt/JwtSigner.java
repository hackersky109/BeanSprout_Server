package iot.utils.jwt;

import iot.utils.crypt.BASE64;
import iot.utils.crypt.SHA256;

public class JwtSigner {
	
	public String sign(String jwtWithoutSign) {
		byte[] signBytes = SHA256.hmac(jwtWithoutSign, JwtConst.KEY);
		return BASE64.encodeUrl(signBytes);
	}
}
