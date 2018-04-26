package iot.service.api.bean.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import iot.utils.exception.UnauthorizedException;
import iot.utils.jwt.JsonWebToken;
import iot.utils.jwt.JwtParser;
import iot.utils.jwt.JwtValidator;


public class RequiredAnnotationHandler {

	public void validate(Object object){
		Class<?> clazz = object.getClass();
		while(clazz != null ) {
			Field[] fds = clazz.getDeclaredFields();
			for(Field fi : fds){
				try {
					fi.setAccessible(true);
					checkByAnnotation(fi, object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new UnauthorizedException("validate failed");
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new UnauthorizedException("validate failed");
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
	
	private void checkByAnnotation(Field field, Object object) throws IllegalArgumentException, IllegalAccessException{
		Annotation[] annotations = field.getDeclaredAnnotations();
		for(Annotation annotation: annotations){
			if(annotation instanceof Required){
				requiredAnnotation(field, annotation, object);
			}
			else if(annotation instanceof AuthToken){
				authTokenAnnotation(field, annotation, object);
			}
			else if(annotation instanceof AuthApiKey){
				authApiKeyAnnotation(field, annotation, object);
			}
		}
	}

	private void authTokenAnnotation(Field field, Annotation annotation, Object object) throws IllegalArgumentException, IllegalAccessException {
		String token = (String) field.get(object);
		JsonWebToken jwt = new JwtParser().parse(token);
		new JwtValidator(jwt).validateSign();
	}
	
	private void authApiKeyAnnotation(Field field, Annotation annotation, Object object) throws IllegalArgumentException, IllegalAccessException{
		AuthApiKey authKey = (AuthApiKey) annotation; 
		String apiKey = (String) field.get(object);
	
		if(ApiKey.isValid(apiKey) == false){
			throw new UnauthorizedException(authKey.message());
		}
	}
	
	private void requiredAnnotation(Field field, Annotation annotation, Object object) throws IllegalArgumentException, IllegalAccessException{
		Required re = (Required) annotation;
		if(field.get(object) == null){
			throw new UnauthorizedException(re.message());
		}
	}
}
