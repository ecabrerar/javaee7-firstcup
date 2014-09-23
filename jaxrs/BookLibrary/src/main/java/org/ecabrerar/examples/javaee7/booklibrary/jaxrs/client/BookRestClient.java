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

package org.ecabrerar.examples.javaee7.booklibrary.jaxrs.client;

import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.ecabrerar.examples.javaee7.booklibrary.mbean.Book;

/**
 *
 * @author ecabrerar
 */
@Stateless
public class BookRestClient {
   private final URI uri;
       private final Client client;
       public BookRestClient() {
             uri = UriBuilder
             .fromUri("http://localhost:8080/BookLibrary/rest/book")
             .port(8080).build();              
             client = ClientBuilder.newClient();
       }
       
       public String addNewBook(Book book){
             Response response = client.target(uri)
             .request()
             .post(Entity.entity(book,MediaType.APPLICATION_XML));
             return response.getStatusInfo().getReasonPhrase();
       }
       
       public List<Book> getBooks(){
             List<Book> books = client.target(uri)
             .request()
             .get(new GenericType<List<Book>>(){});
             return books;
       }
       
       public Book getBook(String bookId){
           
           Book book = client.target(uri)
                   .path("/{id}")
                   .resolveTemplate("bookId", bookId)
                   .request()
                   .get(Book.class);
           
         return book;           
       }
       
       public void close(){
             client.close();
       } 
}
