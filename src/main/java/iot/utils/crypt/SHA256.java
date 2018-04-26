package iot.utils.crypt;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import iot.utils.exception.InternalErrorException;

public class SHA256 {
	private static final String HC256 = "HmacSHA256";
	private static final String SHA256 = "SHA-256";
	private static final String ASCII = "US-ASCII";
	
	public static byte[] hash(String text) {
		try {
			MessageDigest digest = MessageDigest.getInstance(SHA256);
			byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InternalErrorException("SHA256: NoSuchAlgorithm");
		}
	}
	
	public static byte[] hmac(String text, String key) {
		try {
			Mac mac = Mac.getInstance(HC256);
			byte[] keyBytes = key.getBytes(ASCII);
			Key secKey = new SecretKeySpec(keyBytes, HC256);
			mac.init(secKey);
			return mac.doFinal();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InternalErrorException("SHA256: NoSuchAlgorithm");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new InternalErrorException("SHA256: UnsupportedEncoding");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new InternalErrorException("SHA256: InvalidKey");
		}
	}
}
