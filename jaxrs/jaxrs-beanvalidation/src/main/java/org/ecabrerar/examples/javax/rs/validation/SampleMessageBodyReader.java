package org.ecabrerar.examples.javax.rs.validation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author ecabrerar
 * @date Jan 24, 2017
 */
@Provider
public class SampleMessageBodyReader implements MessageBodyReader<Book> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Book readFrom(Class<Book> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Book book = (Book) unmarshaller.unmarshal(entityStream);
			return book;
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

}
