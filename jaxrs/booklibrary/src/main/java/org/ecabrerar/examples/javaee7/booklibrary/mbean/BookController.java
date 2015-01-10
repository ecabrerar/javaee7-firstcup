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

package org.ecabrerar.examples.javaee7.booklibrary.mbean;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.ecabrerar.examples.javaee7.booklibrary.jaxrs.client.BookRestClient;

/**
 *
 * @author ecabrerar
 */
@Named(value = "bk")
@RequestScoped
public class BookController {
    @Inject
    private BookRestClient rc;
    private Book book = new Book();
    List<Book> books = new ArrayList<>();
    FacesContext facesContext = FacesContext.getCurrentInstance();
 
    public BookController() {
    }
 
    public Book getBook() {
        return book;
    }
 
    public void setBook(Book book) {
        this.book = book;
    }
 
    public String addNewBook() {
        String status = "";
        try {
            if (validate()) {
                status = rc.addNewBook(book);
                facesContext
                    .addMessage(status, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, 
                    "New Book added successfully", 
                    book.toString()));
            }
        } catch (Exception ex) {
            facesContext
             .addMessage(status, new FacesMessage(
             FacesMessage.SEVERITY_ERROR, 
             "New Book cannot be added", ex.getMessage()));
        }
       
        return "register.xhtml";
    }
 
    public List<Book> getAllBooks() {        
        books = rc.getBooks();
        return books;
    }
    
    public void getBookById(){
        book = rc.getBook(book.getId());
    }
 
    private boolean validate() {
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            facesContext.addMessage("form:isbn", 
             new FacesMessage(FacesMessage.SEVERITY_WARN, 
             "Invalid ISBN", "Please enter a valid ISBN number"));
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            facesContext.addMessage("form:title", 
             new FacesMessage(FacesMessage
             .SEVERITY_WARN, "Invalid Title", 
             "Please enter a valid title"));
        }
        return facesContext.getMessageList().isEmpty();
    }
}
