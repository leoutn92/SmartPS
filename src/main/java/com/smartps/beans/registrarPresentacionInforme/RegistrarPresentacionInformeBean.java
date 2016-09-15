package com.smartps.beans.registrarPresentacionInforme;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.Alumno;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

public class RegistrarPresentacionInformeBean {
	private int legajo;
	private String nombreAlumno;
	private String psTitle;
	private Alumno alumno = new Alumno();
	private PS ps = new PS();
	private List<PS> pss = new ArrayList<PS>();
	private Date fechaPresentacion = new Date();
	EstadoDao estadoDAO = new EstadoDao();
	PSDao psDao = new PSDao(); 
	InformeFinalDao inFinalDao = new InformeFinalDao();
	PlanDeTrabajoDao planDeTrabajoDao = new PlanDeTrabajoDao();
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public String getPsTitle() {
		return psTitle;
	}
	public void setPsTitle(String psTitle) {
		this.psTitle = psTitle;
	}
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
	public PS searchPsConPlanPresentado(int legajo) {
		// TODO Auto-generated method stub
		return null;
	}
	public String registrarPresentacionInforme() {
		// TODO Auto-generated method stub
		InformeFinal informe = new InformeFinal();
		informe.setFechaDePresentacion(this.fechaPresentacion);
		informe.setPs(ps);
		inFinalDao.save(informe);
		int legajoPs=ps.getAlumno().getLegajo();
		PS newPs = psDao.searchPs(legajoPs,this.getIdEstadoPlanAprobado());
		psDao.updateEstado(newPs.getId(),this.getIdEstadoInformePresentado());
		return "se cargo informe exitosamente";
	}
	
	private int getIdEstadoInformeObservado() {
		// TODO Auto-generated method stub
		return estadoDAO.buscarPorNombre("Informe observado").getId();
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
	public List<PS> getPss() {
		return pss;
	}
	public void setPss(List<PS> pss) {
		this.pss = pss;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public List<LineaTablaInformes> searchPsParaPresentarInforme(CriteriosParaFiltrarPs criterios) {
		// TODO Auto-generated method stub
		List<LineaTablaInformes> tablaInformes= new ArrayList<LineaTablaInformes>(); 
		List<PS> pssConPlanAprobado = psDao.searchPs(criterios,this.getIdEstadoPlanAprobado());
		List<PS> pssConInformeObservado = psDao.searchPs(criterios,this.getIdEstadoInformeObservado());
		pssConPlanAprobado.addAll(pssConInformeObservado);
		List<PS> pssParaPresentarInforme = pssConPlanAprobado;
		for (PS ps: pssParaPresentarInforme) {
			LineaTablaInformes linea=new LineaTablaInformes();
			linea.setLegajo(ps.getAlumno().getLegajo());
			linea.setPsTitle(ps.getTitulo());
			linea.setNombreAlumno(ps.getAlumno().getNombre());
			String nombreEstado = estadoDAO.getById(ps.getEstado().getId());   
			linea.setEstado(nombreEstado);
			PlanDeTrabajo plan = planDeTrabajoDao.getLastByFechaAprobadoDesaprobado(ps.getId());
			if (plan!=null) {
				linea.setDirPlan(plan.getDirDocumentoDigital());
			}
			tablaInformes.add(linea);
		}
		return tablaInformes;
	}	
}