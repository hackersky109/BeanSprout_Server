package iot.service.config.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import iot.service.api.AccountApi;
import iot.service.api.BeanSproutApi;
import iot.service.api.ControllerApi;
import iot.service.api.SensorApi;
import iot.service.api.SensorGroupApi;
import iot.service.api.testapi;


public class ConfigApplication extends Application{

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register resources and features
        classes.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        classes.add(CustomObjMapper.class);
        classes.add(JsonProcessingExceptionMapper.class);
        classes.add(MultiPartFeature.class);
        classes.add(testapi.class);
        classes.add(AccountApi.class);
        classes.add(SensorApi.class);
        classes.add(SensorGroupApi.class);
        classes.add(ControllerApi.class);
        classes.add(BeanSproutApi.class);
        return classes;
    }
}
