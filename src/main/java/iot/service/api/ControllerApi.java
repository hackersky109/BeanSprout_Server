package iot.service.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import iot.service.api.bean.ControllerBean;
import iot.service.api.bean.TokenBean;
import iot.service.api.impl.ControllerApiImpl;




@Path("/api/v2/controllers")
public class ControllerApi {
	
	public ControllerApi(){
		new ResourceConfig().packages("org.glassfish.jersey.examples.jackson")
		.register(JacksonFeature.class);
	}
	
	@GET @Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		JSONObject resp = new JSONObject();
		resp.put("ControllerApi", "hello");
		return Response.ok().entity(resp.toString()).build();
	}

	@POST 
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response newController(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, ControllerBean bean) {
		bean.setApiKeyAndToken(apiKey, token);
		ResponseBuilder res = new ControllerApiImpl().newController(bean);
		return res.build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getControllerList(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new ControllerApiImpl().getControllerList(bean);
		return res.build();
	}
	
	@PUT @Path("{controllerId}/config") 
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response updateController(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("controllerId")String controllerId, org.json.simple.JSONObject config) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new ControllerApiImpl().updateConfig(bean, controllerId, config);
		return res.build();
	}
	
	@GET @Path("{controllerId}/config") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConfig(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token,  @PathParam("controllerId")String controllerId) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new ControllerApiImpl().getConfig(bean, controllerId);
		return res.build();
	}
	
	@PUT @Path("{controllerId}/{mode}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response Controll(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token, @PathParam("controllerId")String controllerId, @PathParam("mode")String mode, @QueryParam("delay_time")Long delay_time) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new ControllerApiImpl().controll(bean, controllerId, mode.toLowerCase(), delay_time);
		return res.build();
	}
	
	@GET @Path("{controllerId}/mode") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getControllerMode(@PathParam("controllerId")String controllerId) {
		ResponseBuilder res = new ControllerApiImpl().getControllerMode(controllerId);
		return res.build();
	}
}
