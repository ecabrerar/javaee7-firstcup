package org.ecabrerar.examples.bean.validation.jaxrs;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.validation.ValidationError;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 * @author ecabrerar
 * @date   Jan 24, 2017
 */
public class BooksResourceTest {

	private static Client client;
	private final String baseUri = "http://localhost:8080/jaxrs-beanvalidation/app/library/books";

        @BeforeClass
        public static void setUp(){
    	    client = ClientBuilder.newClient();
        }

	@Test
	public void getBookTest(){

		Response response = client.target(baseUri)
					  .path("/{isbn}")
					  .resolveTemplate("isbn", "782345689")
					  .request(MediaType.APPLICATION_XML)
					  .get(Response.class);

		List<ValidationError> errors = response.readEntity(new GenericType<List<ValidationError>>() {});


		Assert.assertTrue(errors.isEmpty());

	}
	
	@Test
	public void getBookIsbnEmptyTest(){
		Response response = client.target(baseUri)
			  .path("/{isbn}")
			  .resolveTemplate("isbn", "null")
			  .request(MediaType.APPLICATION_XML)
			  .get(Response.class);

		List<ValidationError> errors = response.readEntity(new GenericType<List<ValidationError>>() {});
		
		Assert.assertTrue(!errors.isEmpty());
		Assert.assertNotNull("Book does not exist for the ISBN requested", errors.get(0));

	}
}
