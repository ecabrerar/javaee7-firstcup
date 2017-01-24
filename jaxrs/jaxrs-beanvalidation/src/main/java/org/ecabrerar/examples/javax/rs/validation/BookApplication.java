/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecabrerar.examples.javax.rs.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ServerProperties;



/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
@ApplicationPath("app/library")
public class BookApplication extends Application{

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet<>();
        resources.add(BooksResource.class);
        resources.add(SampleMessageBodyWriter.class);
        resources.add(SampleMessageBodyReader.class);

        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {

    	Map<String, Object> properties = new HashMap<>();
    	properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

    	return properties;
    }


}
