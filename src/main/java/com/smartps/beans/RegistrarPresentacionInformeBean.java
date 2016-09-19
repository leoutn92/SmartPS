package com.smartps.beans;

import java.util.Date;

import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;

public class RegistrarPresentacionInformeBean {
	private PS ps = new PS();
	public PS getPs() {
		return ps;
	}
	public void setPs(PS ps) {
		this.ps = ps;
	}
	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}
	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	private Date fechaPresentacion = new Date();
	EstadoDao estadoDAO = new EstadoDao();
	PSDao psDao = new PSDao(); 
	InformeFinalDao inFinalDao = new InformeFinalDao();
	public PS searchPsConPlanPresentado(int legajo) {
		// TODO Auto-generated method stub
		return null;
	}
	public String registrarPresentacionInforme() {
		// TODO Auto-generated method stub
		InformeFinal informe = new InformeFinal();
		informe.setFechaDePresentacion(this.fechaPresentacion);
		inFinalDao.save(informe);
		int legajoPs=ps.getAlumno().getLegajo();
		PS newPs = psDao.searchPs(legajoPs,this.getIdEstadoPlanAprobado());
		psDao.updateEstado(newPs.getId(),this.getIdEstadoInformePresentado());
		return "se cargo informe exitosamente";
	}

	public int getIdEstadoInformePresentado() {
		// TODO Auto-generated method stub
		return estadoDAO.buscarPorNombre("Informe presentado").getId();
	}
	public int getIdEstadoPlanAprobado() {
		// TODO Auto-generated method stub
		return estadoDAO.buscarPorNombre("Plan aprobado").getId();
	}

	public PS searchPsConPlanAprobado(int legajo) {
		// TODO Auto-generated method stub
		return psDao.searchPs(legajo,this.getIdEstadoPlanAprobado());
	}
	
}
