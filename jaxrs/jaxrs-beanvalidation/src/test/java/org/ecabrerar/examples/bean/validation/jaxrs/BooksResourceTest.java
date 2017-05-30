package org.ecabrerar.examples.bean.validation.jaxrs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ecabrerar.examples.javax.rs.validation.domain.Book;
import org.glassfish.jersey.server.validation.ValidationError;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ecabrerar
 * @date Jan 24, 2017
 */
public class BooksResourceTest {

	private static Client client;
	private final String baseUri = "http://localhost:8080/jaxrs-beanvalidation/app/library/books";

	private static final Logger LOGGER = Logger.getLogger(BooksResourceTest.class.getName());

	@BeforeClass
	public static void setUp() {
		client = ClientBuilder.newClient();
	}

	@Test
	public void shouldReturnACreatedBook() {

		Response response = client.target(baseUri)
								  .path("/{isbn}")
								  .resolveTemplate("isbn", "782345689")
								  .request(MediaType.APPLICATION_XML).get();

		response.bufferEntity();
		logResponse("shouldReturnACreatedBook", response);

		Assert.assertNotNull(response.readEntity(Book.class));
	}

	@Test
	public void shouldReturnAValidationErrorWhenGettingABook() {
		Response response = client.target(baseUri)
								  .path("/{isbn}")
								  .resolveTemplate("isbn", "test")
								  .request(MediaType.APPLICATION_XML)
								  .get();

		response.bufferEntity();
		logResponse("shouldReturnAValidationErrorWhenGettingABook", response);

		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());

		Set<String> violationsMessageTemplates = getValidationMessageTemplates(response);
		assertEquals(1, violationsMessageTemplates.size());

		assertTrue(violationsMessageTemplates.contains("Book does not exist for the ISBN requested"));

	}


	private void logResponse(String method, Response response) {
		StringBuilder builder = new StringBuilder(method).append("\n");
		builder.append("Response: ").append(response).append("\n");
		builder.append("Entity: ").append(response.readEntity(String.class)).append("\n");
		LOGGER.info(builder.toString());
	}

	private List<ValidationError> getValidationErrorList(final Response response) {
        return response.readEntity(new GenericType<List<ValidationError>>() {});
}

	private Set<String> getValidationMessageTemplates(final Response response) {
		return getValidationMessageTemplates(getValidationErrorList(response));
	}

	private Set<String> getValidationMessageTemplates(final List<ValidationError> errors) {

		return errors
			   .stream()
			   .map(error -> error.getMessageTemplate())
			   .collect(Collectors.toSet());
	}
}
