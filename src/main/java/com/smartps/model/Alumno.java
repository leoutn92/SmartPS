package com.smartps.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class Alumno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int legajo;
	private String nombre;
	private int cicloLectivo;
	private Set<PS> listPS = new HashSet<PS>(0);
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
	public void addPs(PS ps) {
		// TODO Auto-generated method stub
		this.listPS.add(ps);
	}
	
}
