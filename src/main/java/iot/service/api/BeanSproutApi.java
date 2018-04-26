package iot.service.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;

import iot.service.api.bean.DataBean;
import iot.service.api.bean.RecordBean;
import iot.service.api.bean.SensorBean;
import iot.service.api.bean.TokenBean;
import iot.service.api.impl.RecordApiImpl;
import iot.service.api.impl.SensorApiImpl;

@Path("/api/v2/beansprout")
public class BeanSproutApi {
	public BeanSproutApi() {
		new ResourceConfig().packages("org.glassfish.jersey.examples.jackson")
							.register(JacksonFeature.class);
	}
	
	@GET @Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		JSONObject resp = new JSONObject();
		resp.put("BeanSproutApi", "hello");
		return Response.ok().entity(resp.toString()).build();
	}
	
	@POST @Path("/records")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response newSensor(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, RecordBean bean) {
		bean.setApiKeyAndToken(apiKey, token);
		ResponseBuilder res = new RecordApiImpl().newRecord(bean);
		return res.build();
	}
	
	@GET @Path("/{sensorId}/records")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecordList(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("sensorId")String sensorId) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new RecordApiImpl().getRecordList(bean, sensorId);
		return res.build();
	}
	
	@GET @Path("/records/{recordId}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecord(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("recordId")String recordId) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new RecordApiImpl().getRecord(bean, recordId);
		return res.build();
	}
	
	@DELETE @Path("/records/{recordId}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRecord(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("recordId")String recordId) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new RecordApiImpl().deleteRecord(bean, recordId);
		return res.build();
	}
	
}