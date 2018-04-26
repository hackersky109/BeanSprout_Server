package iot.service.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import org.json.simple.JSONObject;

import iot.service.api.bean.AccountBean;
import iot.service.api.bean.TokenBean;
import iot.service.api.impl.AccountApiImpl;

@Path("/api/v2/account")
public class AccountApi {
	public AccountApi() {
		new ResourceConfig().packages("org.glassfish.jersey.examples.jackson")
							.register(JacksonFeature.class);
	}
	

	@GET @Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		JSONObject resp = new JSONObject();
		resp.put("AccountApi", "hello");
		return Response.ok().entity(resp.toString()).build();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(@HeaderParam("apiKey")String apiKey, AccountBean bean) {
		bean.setApiKey(apiKey);
		ResponseBuilder res = new AccountApiImpl().createAccount(bean);
		return res.build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@HeaderParam("apiKey")String apiKey, @HeaderParam("token")String token) {
		TokenBean bean = new TokenBean(apiKey, token);
		ResponseBuilder res = new AccountApiImpl().getAccount(bean);
		return res.build();
	}
	
	@POST @Path("/login")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response login(@HeaderParam("apiKey")String apiKey, AccountBean bean) {
		bean.setApiKey(apiKey);
		ResponseBuilder res = new AccountApiImpl().login(bean);
		return res.build();
	}

}
