/*
 * Copyright 2014 ecabrerar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ecabrerar.examples.javaee7.booklibrary.rest;

import java.net.URI;
import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.ecabrerar.examples.javaee7.booklibrary.entities.Book;
import org.ecabrerar.examples.javaee7.booklibrary.jaxb.Books;
import org.ecabrerar.examples.javaee7.booklibrary.service.BookService;

/**
 *
 * @author ecabrerar
 */
@Path("/book")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@ApplicationScoped
public class BookRestService {

    @Inject
    BookService bookService;

    @Context
    private UriInfo uriInfo;

    @POST
    public Response createBook(Book book) {
        Book  newBook = bookService.createBook(book);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newBook.getId().toString()).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") String id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") String id) {
        Book book = bookService.getBook(id);

        if (book == null) {
            throw new NotFoundException();
        }
        return Response.ok(book).build();
    }

    @GET
    public Books getBooks() {       
        return new Books(bookService.findAllBooks());
    }

}
