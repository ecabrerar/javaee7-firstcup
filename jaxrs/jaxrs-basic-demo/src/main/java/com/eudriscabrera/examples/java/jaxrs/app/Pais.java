package com.eudriscabrera.examples.java.jaxrs.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ecabrerar
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pais  {

	private int id;
	private String nombre;
	
	public Pais() {		
	}

	public Pais(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
