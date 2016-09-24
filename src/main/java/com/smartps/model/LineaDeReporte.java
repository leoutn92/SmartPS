package com.smartps.model;

import java.util.Date;

public class LineaDeReporte {

	private Date fechaDePresentacion;
	private String titulo;
	private String estado;
	private String area;
	private String tipoActividad;
	private String alumno;
	private int ingreso;
	
	
	public Date getFechaDePresentacion() {
		return fechaDePresentacion;
	}
	
	public void setFechaDePresentacion(Date fechaDePresentacion) {
		this.fechaDePresentacion = fechaDePresentacion;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getTipoActividad() {
		return tipoActividad;
	}
	
	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}
	
	public String getAlumno() {
		return alumno;
	}
	
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	
	public int getIngreso() {
		return ingreso;
	}
	
	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}
	
}
