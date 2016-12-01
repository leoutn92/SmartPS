package com.smartps.model;

import java.sql.Blob;
import java.util.Date;

public class DocumentoPresentable  {
	private Date fechaDePresentacion;
	private Date fechaAprobDesaprob;
	private String observaciones;
	private Blob file;
	private Integer ordenanza;
	private String dirDocumentoDigital;
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
	public Integer getOrdenanza() {
		return ordenanza;
	}
	public void setOrdenanza(Integer ordenanza) {
		this.ordenanza = ordenanza;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public String getDirDocumentoDigital() {
		return dirDocumentoDigital;
	}
	public void setDirDocumentoDigital(String dirDocumentoDigital) {
		this.dirDocumentoDigital = dirDocumentoDigital;
	}
}
