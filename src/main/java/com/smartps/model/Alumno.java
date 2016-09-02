package com.smartps.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.lang.Integer;
public class Alumno implements Serializable {
	private int legajo;
	private String nombre;
	private int cicloLectivo;
	private Set<PS> listPS = new HashSet<PS>(0);
	public Alumno() {
		
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCicloLectivo() {
		return cicloLectivo;
	}
	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}
	public Set<PS> getListPS() {
		return listPS;
	}
	public void setListPS(Set<PS> listPS) {
		this.listPS = listPS;
	}
	
}
