/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecabrerar.examples.libraryapp.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



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
        resources.add(BookCollectionWriter.class);
        resources.add(BookWriter.class);
        resources.add(BookResource.class);
        
        return resources;
    }

    
}
