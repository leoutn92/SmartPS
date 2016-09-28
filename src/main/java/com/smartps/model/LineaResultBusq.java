package com.smartps.model;

import java.util.Date;

public class LineaResultBusq {

	private String titulo;
	private Date fechaDePresentacion;
	private Date fechaAprobDesaprob;
	private String nroDisposicion;
	private String observaciones;
	private String estado;
	private String area;
	private String organizacion;
	private String tipoActividad;
	private int legajo;
	private String alumno;
	private int cicloLectivo;
	private int cuatrimestre;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFechaDePresentacion() {
		return fechaDePresentacion;
	}
	public void setFechaDePresentacion(Date fechaDePresentacion) {
		this.fechaDePresentacion = fechaDePresentacion;
	}
	public Date getFechaAprobDesaprob() {
		return fechaAprobDesaprob;
	}
	public void setFechaAprobDesaprob(Date fechaAprobDesaprob) {
		this.fechaAprobDesaprob = fechaAprobDesaprob;
	}
	public String getNroDisposicion() {
		return nroDisposicion;
	}
	public void setNroDisposicion(String nroDisposicion) {
		this.nroDisposicion = nroDisposicion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	public String getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	public String getTipoActividad() {
		return tipoActividad;
	}
	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public int getCicloLectivo() {
		return cicloLectivo;
	}
	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}
	public int getCuatrimestre() {
		return cuatrimestre;
	}
	public void setCuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}
	
}
