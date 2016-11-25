package com.smartps.model;

import java.util.Date;

public class LineaVence {

	private String titulo;
	private String alumno;
	private int legajo;
	private String estado;
	private Date fechaAprobacion;
	private Date fechaVence;
	private long diasRestantes;
	
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAlumno() {
		return alumno;
	}
	
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	
	public int getLegajo() {
		return legajo;
	}
	
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	
	public Date getFechaVence() {
		return fechaVence;
	}
	
	public void setFechaVence(Date fechaVence) {
		this.fechaVence = fechaVence;
	}

	public long getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(long diasRestantes) {
		this.diasRestantes = diasRestantes;
	}

}