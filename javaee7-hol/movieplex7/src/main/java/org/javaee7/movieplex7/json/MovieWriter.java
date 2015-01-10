package org.javaee7.movieplex7.json;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.javaee7.movieplex7.entities.Movie;

/**
 *
 * @author ecabrerar
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MovieWriter implements MessageBodyWriter<Movie> {

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Movie.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Movie t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Movie t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(out);
        gen.writeStartObject()
                .write("id", t.getId())
                .write("name", t.getName())
                .write("actors", t.getActors())
                .writeEnd();
        gen.flush();
    }

}
