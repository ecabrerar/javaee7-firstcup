package com.eudriscabrera.examples.java;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author ecabrerar
 * 
 *
 */
public class AppDemoClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		String baseUri = "http://localhost:8080/jaxrs-basic-demo/rest";

		// retrieve all paises
		Response resultAll = client.target(baseUri)
				          		.path("/paises/todos")
				          		.request(MediaType.APPLICATION_JSON)
				          		.get(Response.class);

		

		System.out.println("======== Retrieve All ========");		
		System.out.println(resultAll.readEntity(String.class));
		
		System.out.println("======== End Retrieve All ========");		
		
		// retrieve for id
		Response resultForId = client.target(baseUri)
						          		.path("/paises/{id}")
						          		.resolveTemplate("id", 1)
						          		.request(MediaType.APPLICATION_JSON)
						          		.get(Response.class);
		
		
		
		System.out.println("======== Retrieve For Id ========");
		System.out.println(resultForId.readEntity(String.class));		
		System.out.println("======== End Retrieve For Id ========");

	}
}
