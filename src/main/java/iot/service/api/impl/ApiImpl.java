package iot.service.api.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.simple.JSONObject;

public class ApiImpl {
	protected JSONObject body;
	
	protected ResponseBuilder buildResponse() {
		System.out.println(body.toJSONString());
		int status = (int) body.get("status");
		return Response.status(status).entity(body.toJSONString()); 
	}
}
