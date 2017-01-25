package org.ecabrerar.examples.bean.validation.jaxrs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ecabrerar.examples.javax.rs.validation.domain.Book;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ecabrerar
 * @date   Jan 24, 2017
 */
public class BooksCollectionTest {

	private static final List<Book> books = new ArrayList<>();

	@BeforeClass
	public static void setup() {
		books.add(new Book("Java EE development using GlassFish Aplication Server", "782345689", "David Heffinger"));
		books.add(new Book("Java 7 JAX-WS Web Services", "123456789", "Deepak Vohra"));
		books.add(new Book("Netbeans IDE7 CookBook", "2234555567", "Rhawi Dantas"));
		books.add(new Book("Getting Started with RESTful WebServices", "11233333", "Bhakti Mehta, Masoud Kalali"));
	}

	@Test
	public void getBookTest(){
	    String isbn = "782345689";

	    Optional<Book> opBook=	books.stream()
    								.filter(t -> t.getIsbn().equals(isbn))
    								.peek(System.out::println)
    								.findAny();

	    Assert.assertTrue(opBook.isPresent());
	}

}
