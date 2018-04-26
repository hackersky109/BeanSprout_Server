package iot.service.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;

import iot.service.api.bean.GroupDataBean;
import iot.service.api.bean.SensorGroupBean;
import iot.service.api.impl.SensorGroupApiImpl;

@Path("/api/v2/sensorgroups")
public class SensorGroupApi {
	public SensorGroupApi(){
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
	public Response newSensorGroup(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, SensorGroupBean bean) {
		bean.setApiKeyAndToken(apiKey, token);
		ResponseBuilder res = new SensorGroupApiImpl().newSensorGroup(bean);
		return res.build();
	}
	
	@POST @Path("/{sensorGroupId}/data")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response newData(@HeaderParam("uploadKey")String uploadKey, @PathParam("sensorGroupId")String sensorGroupId, GroupDataBean bean){
		bean.setUploadKey(uploadKey).setSensorGroupId(sensorGroupId);
		ResponseBuilder res = new SensorGroupApiImpl().newData(bean);
		return res.build();	
	}
}
