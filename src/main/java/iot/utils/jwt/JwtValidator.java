package iot.utils.jwt;

import iot.utils.Timestamp;
import iot.utils.exception.UnauthorizedException;

public class JwtValidator {
	public final JsonWebToken jwt;
	
	public JwtValidator(JsonWebToken jwt) {
		this.jwt = jwt;
	}

	public JwtValidator validateSign() {
		String tSign = jwt.getSignature();
		String ansSign = new JwtBuilder(jwt.getHeader(), jwt.getBody()).jwtSign();
		if(tSign == null || !tSign.equals(ansSign))
			throw new UnauthorizedException("[Token] wrong signature");
		return this;
	}
	
	public JwtValidator validateIssuer(String iss) {
		String tIss = jwt.getBody().getIss();
		if(tIss == null || !tIss.equals(iss))
			throw new UnauthorizedException("[Token] wrong claim");
		return this;
	}
	
	public JwtValidator validateSubject(String sub) {
		String tSub = jwt.getBody().getSub();
		if(tSub == null || !tSub.equals(sub))
			throw new UnauthorizedException("[Token] wrong claim");
		return this;
	}

	public JwtValidator validateAudience(String aud) {
		String tAud = jwt.getBody().getAud();
		if(tAud == null || !tAud.equals(aud))
			throw new UnauthorizedException("[Token] wrong claim");
		return this;
	}

	public JwtValidator isExpired() {
		if(Timestamp.now() > jwt.getBody().getExp())
			throw new UnauthorizedException("[Token] token is expired");
		return this;
	}
}
