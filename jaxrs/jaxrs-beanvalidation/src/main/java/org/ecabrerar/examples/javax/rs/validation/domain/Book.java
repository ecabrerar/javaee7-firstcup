package org.ecabrerar.examples.javax.rs.validation.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
@XmlRootElement
public class Book {

	@NotNull(message = "Name can not be null")
	private String name;

	@Size(min = 13, max = 13, message = "Enter a valid isbn")
	private String isbn;

	@NotEmpty(message = "Author is missing")
	private String author;

	// JAXB requires this
	public Book() {
	}

	public Book(String name, String isbn, String author) {
		this.name = name;
		this.isbn = isbn;
		this.author = author;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setAuthor(String author) {
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
		return new StringBuilder().append(" name").append("=").append(name).append(" isbn").append("=").append(isbn)
				.append(" author").append("=").append(author).toString();
	}

}
