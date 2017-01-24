/**
 *
 */
package org.ecabrerar.examples.javax.rs.validation;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * @author ecabrerar
 * @date Jan 24, 2017
 */
@Provider
public class SampleMessageBodyWriter implements MessageBodyWriter<Book> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Book book, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Book book, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(book, entityStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
