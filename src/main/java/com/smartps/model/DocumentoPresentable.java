package com.smartps.model;

import java.util.Date;

public class DocumentoPresentable extends DocumentoDelAlumno {
	private int legajo;
	private Date fechaDePresentacion;
	private Date fechaAprobDesaprob;
	private String observaciones;
	private String DirDocumentoDigital;
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getDirDocumentoDigital() {
		return DirDocumentoDigital;
	}
	public void setDirDocumentoDigital(String dirDocumentoDigital) {
		DirDocumentoDigital = dirDocumentoDigital;
	}
}
