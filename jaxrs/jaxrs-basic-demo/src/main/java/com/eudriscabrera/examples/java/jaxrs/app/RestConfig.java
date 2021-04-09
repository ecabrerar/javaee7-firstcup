package com.eudriscabrera.examples.java.jaxrs.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author ecabrerar
 *
 */
@ApplicationPath("rest")
public class RestConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<>();
		classes.add(ServicioRestPais.class);
		
		
		return classes;
	}

}
