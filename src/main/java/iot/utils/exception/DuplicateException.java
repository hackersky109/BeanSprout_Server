package iot.utils.exception;

@SuppressWarnings("serial")
public class DuplicateException extends ErrorException {
	
	public DuplicateException() {
		errorCode = 409;
	}
	
	public DuplicateException(String message) {
		super(message);
		errorCode = 409;
	}
}
