package com.smartps.beans.desicionDelConsejoInforme;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import com.smartps.beans.registrarPresentacionInforme.CriteriosParaFiltrarPs;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.model.Alumno;
import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
@ManagedBean
@ViewScoped
public class DesicionDelConsejoInformes {
	private InformeFinalDao iDao= new InformeFinalDao();
	private PSDao pDao = new PSDao();
	private EstadoDao eDao = EstadoDao.getInstance();
	private String dirInforme;
	private boolean renderedInformeDigital;
	private List<LineaInformeParaDecision> tablaInformesParaDecision = new ArrayList<LineaInformeParaDecision>();
	private String nombreAlumno;
	private String psTitle;
	private int legajo;  
	
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

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public InformeFinalDao getiDao() {
		return iDao;
	}

	public void setiDao(InformeFinalDao iDao) {
		this.iDao = iDao;
	}

	public PSDao getpDao() {
		return pDao;
	}

	public void setpDao(PSDao pDao) {
		this.pDao = pDao;
	}

	public EstadoDao geteDao() {
		return eDao;
	}

	public void seteDao(EstadoDao eDao) {
		this.eDao = eDao;
	}
	
	public void updateTablaInformesParaDecision(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		this.updateTablaInformesParaDecision();
		this.dirInforme=null;
		this.renderedInformeDigital=false;
	}

	public void updateTablaInformesParaDecision() {
		// TODO Auto-generated method stub
		CriteriosParaFiltrarPs newCriterios=new CriteriosParaFiltrarPs();
		if (this.nombreAlumno!=null) {
			if (this.nombreAlumno.isEmpty()) {
				this.nombreAlumno=null;
			};
		}
		if (this.psTitle!=null) {
			if (this.psTitle.isEmpty()) {
				this.psTitle=null;	
			}
		}
		newCriterios.setLegajo(this.legajo);
		newCriterios.setNombreAlumno(this.nombreAlumno);
		newCriterios.setPsTitle(this.psTitle);
		Estado estadoInformePresentado = eDao.getEstadoInformePresentado();
		int idEstadoPresentado = estadoInformePresentado.getId();
		List<PS> pss= pDao.searchPs(newCriterios,idEstadoPresentado);
		List<LineaInformeParaDecision> tabla = new ArrayList<LineaInformeParaDecision>();
		for (PS p:pss) {
			LineaInformeParaDecision linea = new LineaInformeParaDecision(); 
			Alumno alumno = p.getAlumno();
			linea.setNombreAlumno(alumno.getNombre());
			linea.setLegajo(alumno.getLegajo());
			linea.setEstado(p.getEstado().getNombre());
			linea.setPsTitle(p.getTitulo());
			int idps = p.getId();
			linea.setIdInforme(iDao.getLastByFechaAprobadoDesaprobado(idps).getId());
			tabla.add(linea);
		}
		this.tablaInformesParaDecision = tabla;
	}

	public List<LineaInformeParaDecision> getTablaInformesParaDecision() {
		return tablaInformesParaDecision;
	}

	public void setTablaInformesParaDecision(List<LineaInformeParaDecision> tablaInformesParaDecision) {
		this.tablaInformesParaDecision = tablaInformesParaDecision;
	}

	public String getDirInforme() {
		return dirInforme;
	}

	public void setDirInforme(String dirInforme) {
		this.dirInforme = dirInforme;
	}

	public boolean isRenderedInformeDigital() {
		return renderedInformeDigital;
	}

	public void setRenderedInformeDigital(boolean renderedInformeDigital) {
		this.renderedInformeDigital = renderedInformeDigital;
	}
	public LineaInformeParaDecision getLinea(int idInforme) {
		// TODO Auto-generated method stub
		for (LineaInformeParaDecision l:this.tablaInformesParaDecision) {
			if (l.getIdInforme()==idInforme) {
				return l; 
			}
		}
		return null;
	}
	
	

	public InformeFinal evaluar(int idInforme) {
		// TODO Auto-generated method stub
		LineaInformeParaDecision linea = this.getLinea(idInforme);
		InformeFinal informe = iDao.findById(linea.getIdInforme());	
		informe.setFechaAprobDesaprob(linea.getFechaEvaluacion());
		informe.setObservaciones(linea.getObservaciones());
		informe.setOrdenanza(linea.getOrdenanza());
		iDao.update(informe);
		return informe;
	}

	public void aprobar(int idInforme) {
		// TODO Auto-generated method stub
		InformeFinal informe = this.evaluar(idInforme);
		Estado informeAprobado = eDao.getEstadoInformeAprobado();
		PS ps = pDao.findById(informe.getPs().getId()); 
		ps.setEstado(informeAprobado);
		pDao.update(ps);
	}

	public void desaprobar(int idInforme) {
		// TODO Auto-generated method stub
		InformeFinal informe = this.evaluar(idInforme);
		Estado informeObservado = eDao.getEstadoInformeObservado();
		PS ps = pDao.findById(informe.getPs().getId()); 
		ps.setEstado(informeObservado);
		pDao.update(ps);
	}
	
	public void desaprobar(ActionEvent event) {
		int idInforme =(int) event.getComponent().getAttributes().get("linea");
		this.evaluar(idInforme);
		this.desaprobar(idInforme);
		this.updateTablaInformesParaDecision();
	}
	public void aprobar(ActionEvent event) {
		int idInforme =(int) event.getComponent().getAttributes().get("linea");
		this.evaluar(idInforme);
		this.aprobar(idInforme);
		FacesMessage message = new FacesMessage("Bien hecho! :)", "este plan es ahora un plan aprobado");
        FacesContext.getCurrentInstance().addMessage(null, message);
		this.updateTablaInformesParaDecision();
	}
	@PostConstruct
	public void init(){
		this.updateTablaInformesParaDecision();
	}
	
}
