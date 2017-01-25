/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.libraryapp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;


/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
@Singleton
public class BookService {

    private static final Map<String, Book> books = new HashMap<>();
   
    @PostConstruct()
    public void init(){
        addBook(new Book("Java EE development using GlassFish Aplication Server", "782345689", "David Heffinger"));
        addBook(new Book("Java 7 JAX-WS Web Services", "123456789", "Deepak Vohra"));
        addBook(new Book("Netbeans IDE7 CookBook", "2234555567", "Rhawi Dantas"));
        addBook(new Book("Getting Started with RESTful WebServices", "11233333", "Bhakti Mehta, Masoud Kalali"));
    }

    public void addBook(Book book) {
        books.put(book.getName(), book);
    }

    public int getSize() {
        return books.size();
    }

    public Book deleteBook(String isdn) {
        return books.remove(isdn);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

}
