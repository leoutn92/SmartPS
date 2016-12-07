package com.smartps.model;

import java.util.Date;

public class LineaVence {

	private String titulo;
	private String alumno;
	public PS getPs() {
		return ps;
	}


	public void setPs(PS ps) {
		this.ps = ps;
	}

	private int legajo;
	private String estado;
	private Date fechaAprobacion;
	private Date fechaVence;
	private PS ps;
	private int diasRestantes;
	
	public String getDiasRestantesString(){
		if (diasRestantes<0){
			return ("Plan Vencido hace: " + new Integer(-1*diasRestantes).toString()+ " dias");
		}
		return ("Dias para vencer " + new Integer(diasRestantes).toString());
	}
	
	
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

	public int getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(int diasRestantes) {
		this.diasRestantes = diasRestantes;
	}



}