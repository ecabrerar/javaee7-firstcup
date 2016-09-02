/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.libraryapp.domain;

/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
public class Book {

    private final String name;
    private final String isbn;
    private final String author;

    public Book(String name, String isbn, String author) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(" name").append("=").append(name)
                .append(" isbn").append("=").append(isbn)
                .append(" author").append("=").append(author)
                .toString();
    }

}
