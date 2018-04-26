package iot.service.config.application;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import iot.utils.mqtt.Mqtt;

@Provider
public class CustomObjMapper implements ContextResolver<ObjectMapper> {
    final ObjectMapper defaultObjectMapper;
        
    public CustomObjMapper() {
    		System.out.println("FUCKKKKKKKK_YO");
        defaultObjectMapper = createDefaultMapper();
//        try {
//        		new Mqtt().start();
//		} catch (Exception e) {
//			System.out.println("####Reconnected!");
//			new Mqtt().start();
//		}
    }
 
    @Override
    public ObjectMapper getContext(Class<?> type) {
    	return defaultObjectMapper;
        
    }
    
    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(SerializationFeature.INDENT_OUTPUT, true);
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //result.configure(SerializationFeature., state)
        result.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES , true);
        result.configure(DeserializationFeature.WRAP_EXCEPTIONS, false);
        return result;
    }
    
}
