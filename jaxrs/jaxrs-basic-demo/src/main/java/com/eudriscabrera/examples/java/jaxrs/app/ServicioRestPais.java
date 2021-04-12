package com.eudriscabrera.examples.java.jaxrs.app;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author ecabrerar
 *
 */
@Path("/paises")
public class ServicioRestPais {

	@GET
	@Path("/todos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarTodas() {
		return Response.ok(new GenericEntity<List<Pais>>(getPaises()) {
		}).build();
	}
	
	

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarPorId(@PathParam("id") int id) {
		List<Pais> lista = getPaises();

		return Response.ok(lista.stream().filter(pais -> pais.getId() == id).findAny()
				.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND))).build();
	}

	private List<Pais> getPaises() {
		List<Pais> lista = new ArrayList<>();
		lista.add(new Pais(1, "República Dominicana"));
		lista.add(new Pais(2, "Venezuela"));
		lista.add(new Pais(3, "Nicaragua"));
		lista.add(new Pais(4, "Uruguay"));
		lista.add(new Pais(5, "Haiti"));

		return lista;
	}

}
