package org.ecabrerar.examples.javax.rs.validation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 * @author ecabrerar
 * @date Jan 24, 2017
 */
@Singleton
public class BooksCollection {

	private static final List<Book> books = new ArrayList<>();

	@PostConstruct()
	public void init() {
		books.add(new Book("Java EE development using GlassFish Aplication Server", "782345689", "David Heffinger"));
		books.add(new Book("Java 7 JAX-WS Web Services", "123456789", "Deepak Vohra"));
		books.add(new Book("Netbeans IDE7 CookBook", "2234555567", "Rhawi Dantas"));
		books.add(new Book("Getting Started with RESTful WebServices", "11233333", "Bhakti Mehta, Masoud Kalali"));
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public Book getBook(String isbn) {
		return books.stream()
				.filter(t -> t.getIsbn().equals(isbn))
				.findAny()
				.orElse(null);
	}

	public int getSize() {
		return books.size();
	}

	public List<Book> getBooks() {
		return books;
	}
}
