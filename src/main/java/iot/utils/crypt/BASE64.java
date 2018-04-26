package iot.utils.crypt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import iot.utils.exception.BadRequestException;
import iot.utils.exception.InternalErrorException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BASE64 {
	private static final String ASCII = "US-ASCII";
	private static final Base64.Decoder decoder = Base64.getDecoder();;
	private static final Base64.Encoder encoder = Base64.getEncoder();;
	
	// Encode
	public static String encode(String text) {
		try {
			return encoder.encodeToString(text.getBytes(ASCII));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new InternalErrorException("SHA256: UnsupportedEncoding");
		}
	}
	
	public static String encode(byte[] textBytes) {
		return encoder.encodeToString(textBytes);
	}
	
	public static String encodeUrl(String text) {
		try {
			return encodeUrl(text.getBytes(ASCII));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new InternalErrorException("BASE64: UnsupportedEncoding");
		}
	}
	
	public static String encodeUrl(byte[] textBytes) {
		try {
			byte[] bytes = encoder.encode(textBytes);
			bytes = removePadding(bytes);
			for (int i = 0; i < bytes.length; i++) {
				if (bytes[i] == '+') {
					bytes[i] = '-';
		        } else if (bytes[i] == '/') {
		            bytes[i] = '_';
		        }
		    }
		    return new String(bytes, ASCII);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new InternalErrorException("BASE64: UnsupportedEncoding");
		}
	}
	
	// Decode
	public static byte[] decode(String encodedText){
		return decoder.decode(encodedText);
	}
	
	public static String decodeToString(String encodedText){
		try {
			return new String(decoder.decode(encodedText), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new BadRequestException("UTF-8 encode failed.");
		}
	}
	
	public static JSONObject decodeUrlToJSONObject(String encodedText) {
		try {
			return (JSONObject) (new JSONParser().parse(BASE64.decodeUrl(encodedText)));
		} catch (ParseException e) {
			throw new BadRequestException("Parse BASE64 to JSONObject failed");
		}
	}
	
	public static String decodeUrl(String encodedText) {
		char[] chars = encodedText.toCharArray();

        // add back padding if removed
        chars = ensurePadding(chars);

        // Replace url-friendly chars back to normal Base64 chars:
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '-') {
                chars[i] = '+';
            } else if (chars[i] == '_') {
                chars[i] = '/';
            }
        }

        String base64Text = new String(chars);

        return decodeToString(base64Text);
	}
	
	protected static byte[] removePadding(byte[] bytes) {
        byte[] result = bytes;

        int paddingCount = 0;
        for (int i = bytes.length - 1; i > 0; i--) {
            if (bytes[i] == '=') {
                paddingCount++;
            } else {
                break;
            }
        }
        if (paddingCount > 0) {
            result = new byte[bytes.length - paddingCount];
            System.arraycopy(bytes, 0, result, 0, bytes.length - paddingCount);
        }

        return result;
    }
	
    protected static char[] ensurePadding(char[] chars) {
        char[] result = chars; 

        int paddingCount = 0;

        int remainder = chars.length % 4;
        if (remainder == 2 || remainder == 3) {
            paddingCount = 4 - remainder;
        }

        if (paddingCount > 0) {
            result = new char[chars.length + paddingCount];
            System.arraycopy(chars, 0, result, 0, chars.length);
            for (int i = 0; i < paddingCount; i++) {
                result[chars.length + i] = '=';
            }
        }

        return result;
    }
}
