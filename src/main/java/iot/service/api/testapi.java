package iot.service.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import iot.db.connet.dynamodb.setting.DynamoDBClientFactory;
import iot.db.helper.dynamodb.query.AccountQueryDynamoHelper;
import iot.db.model.Account;
import iot.service.api.bean.ApiBean;

@Path("/testapi")
public class testapi {
	public testapi() {
		new ResourceConfig().packages("org.glassfish.jersey.examples.jackson")
							.register(JacksonFeature.class);
	}
	
	@GET @Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		JSONObject resp = new JSONObject();
		new AccountQueryDynamoHelper("Account").scanAttributeN("email", "hackersky109@gmail.com");;
		return Response.ok().entity(resp.toString()).build();
	}
	
	@GET @Path("/testt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testt() {
		JSONObject resp = new JSONObject();
		Account acc = new Account();
		resp.put("description", "hello");
		DynamoDBMapper mapper = new DynamoDBMapper(DynamoDBClientFactory.getDynamoDBClient());
		acc = mapper.load(Account.class, "hackersky109@gmail.com");
		return Response.ok().entity(acc).build();
	}
	
	@POST @Path("/new")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(@HeaderParam("apiKey")String apiKey) {	
		ApiBean bean = new ApiBean(apiKey);
		bean.validate();
		JSONObject resp = new JSONObject();
		resp.put("description", apiKey);


		
		return  Response.ok().entity(resp.toString()).build();
	}
}
