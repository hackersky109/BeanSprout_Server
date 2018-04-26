package iot.utils.exception;

@SuppressWarnings("serial")
public class UnauthorizedException extends ErrorException {
	
	public UnauthorizedException() {
		errorCode = 401;
	}
	
	public UnauthorizedException(String message) {
		super(message);
		errorCode = 401;
	}
}
