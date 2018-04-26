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
import iot.service.api.bean.SensorBean;
import iot.service.api.bean.TokenBean;
import iot.service.api.impl.SensorApiImpl;

@Path("/api/v2/sensors")
public class SensorApi {
	public SensorApi() {
		new ResourceConfig().packages("org.glassfish.jersey.examples.jackson")
							.register(JacksonFeature.class);
	}
	
	@GET @Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		JSONObject resp = new JSONObject();
		resp.put("SensorApi", "hello");
		return Response.ok().entity(resp.toString()).build();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response newSensor(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, SensorBean bean) {
		bean.setApiKeyAndToken(apiKey, token);
		ResponseBuilder res = new SensorApiImpl().newSensor(bean);
		return res.build();
	}
	
	@DELETE @Path("/{sensorId}")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response deleteSensor(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("sensorId")String sensorId) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new SensorApiImpl().deleteSensor(bean, sensorId);
		return res.build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensorList(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new SensorApiImpl().getSensorList(bean);
		return res.build();
	}
	
	@GET @Path("/{sensorId}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensor(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("sensorId")String sensorId) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new SensorApiImpl().getSensor(bean, sensorId);
		return res.build();
	}
	
	@POST @Path("/{sensorId}/data")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response newData(@HeaderParam("uploadKey")String uploadKey, @PathParam("sensorId")String sensorId, DataBean bean){
		bean.setUploadKey(uploadKey).setSensorId(sensorId);
		System.out.println(bean.getSensorId()+"\n"+bean.getData());
		ResponseBuilder res = new SensorApiImpl().newData(bean);
		return res.build();	
	}
	
	@GET @Path("/{sensorId}/data") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("sensorId")String sensorId, @QueryParam("from")String from, @QueryParam("to")String to) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new SensorApiImpl().geData(bean, sensorId, from, to);
		return res.build();
	}
	
}