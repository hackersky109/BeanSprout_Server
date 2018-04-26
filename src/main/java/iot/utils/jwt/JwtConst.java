package iot.utils.jwt;

import iot.utils.PropertiesLoader;

public class JwtConst {
	public static final String TYP = "typ";
	public static final String ALG = "alg";
	public static final String ISS = "iss";
	public static final String SUB = "sub";
	public static final String AUD = "aud";
	public static final String IAT = "iat";
	public static final String EXP = "exp";

	public static final String SEPARATOR = ".";
	public static final String KEY = PropertiesLoader.getProperty("iot.jwt.signature.key");
	public static final String DEFAULT_TYP = PropertiesLoader.getProperty("iot.jwt.header.typ");
	public static final String DEFAULT_ALG = PropertiesLoader.getProperty("iot.jwt.header.alg");
	public static final String DEFAULT_ISS = PropertiesLoader.getProperty("iot.jwt.body.issuer");
	public static final String DEFAULT_SUB = PropertiesLoader.getProperty("iot.jwt.body.subject");
	public static final long DEFAULT_EXP_DURATION = Long.parseLong(PropertiesLoader.getProperty("iot.jwt.body.exp.duration"));
}
