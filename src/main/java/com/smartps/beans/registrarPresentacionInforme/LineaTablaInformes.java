package com.smartps.beans.registrarPresentacionInforme;

import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.smartps.model.PS;
@ManagedBean
@ApplicationScoped
public class LineaTablaInformes {
	private String nombreAlumno;
	private int legajo;
	private String estado;
	private String dirPlan;
	private String psTitle;
	private Date fechaPresentacion;
	private PS ps;
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
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
	public String getDirPlan() {
		return dirPlan;
	}
	public void setDirPlan(String dirPlan) {
		this.dirPlan = dirPlan;
	}
	public String getPsTitle() {
		return psTitle;
	}
	public void setPsTitle(String psTitle) {
		this.psTitle = psTitle;
	}
	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}
	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}
	public PS getPs() {
		return ps;
	}
	public void setPs(PS ps) {
		this.ps = ps;
	}
	
}
