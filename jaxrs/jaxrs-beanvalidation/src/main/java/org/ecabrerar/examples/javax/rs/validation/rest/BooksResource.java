package org.ecabrerar.examples.javax.rs.validation.rest;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ecabrerar.examples.javax.rs.validation.domain.Book;
import org.ecabrerar.examples.javax.rs.validation.domain.BooksCollection;

/**
 *
 * @author ecabrerar
 * @date Jan 24, 2017
 */
@Path("books")
@ValidateOnExecution(type=ExecutableType.GETTER_METHODS)
public class BooksResource {

	@Inject
	BooksCollection booksCollection;

	@GET
	@Path("{isbn}")
	@Produces(MediaType.APPLICATION_XML)
	@NotNull(message="Book does not exist for the ISBN requested")
	public Book getBook(@PathParam("isbn") String isbn){
		return booksCollection.getBook(isbn);
	}
}
