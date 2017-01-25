package org.ecabrerar.examples.javax.rs.validation.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
@XmlRootElement
public class Book {

    private String name;
    private String isbn;
    private String author;

   //JAXB requires this
	public Book() {
	}

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
