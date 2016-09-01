/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.libraryapp.rest;

import java.util.List;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import org.ecabrerar.examples.libraryapp.domain.Book;
import org.ecabrerar.examples.libraryapp.domain.BookService;

/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
@Path("/books")
public class BookResource {

    @Inject
    BookService bookService;

    @GET   
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public List<Book> browseCollection() {
        return bookService.getBooks();
    }

    @DELETE
    @Path("{name}")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.TEXT_PLAIN})
    public Book checkoutBook(@PathParam("name") String nameOfBook) {
        return bookService.deleteBook(nameOfBook);
    }

    @POST
    @Path("{name}")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes({MediaType.TEXT_PLAIN})
    public String returnBook(@PathParam("name") String nameOfBook) {
        return "Successfully returned Book " + nameOfBook;
    }

    /**
     * Asynchronously reply to placing a book on hold after sleeping for
     * sometime
     *
     */
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("hold/{name}")
    public void asyncEcho(@PathParam("name") final String name, @Suspended final AsyncResponse ar) {
        Executors.newSingleThreadExecutor().submit(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    ar.cancel();
                }
                ar.resume("Placed a hold for " + name);
            }
        });
    }
}
