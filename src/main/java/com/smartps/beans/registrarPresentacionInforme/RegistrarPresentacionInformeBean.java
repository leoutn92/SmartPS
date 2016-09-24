package com.smartps.beans.registrarPresentacionInforme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.Alumno;
import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
@ManagedBean
@ViewScoped
public class RegistrarPresentacionInformeBean implements Serializable {
	int legajo;
	String dirPlan;
	LineaTablaInformes linea;
	String nombreAlumno="";
	String psTitle="";
	CriteriosParaFiltrarPs criterios = new CriteriosParaFiltrarPs();
	Alumno alumno = new Alumno();
	PS ps = new PS();
	List<PS> pss = new ArrayList<PS>();
	Date fechaPresentacion = new Date();
	EstadoDao estadoDAO = new EstadoDao();
	PSDao psDao = new PSDao(); 
	InformeFinalDao inFinalDao = new InformeFinalDao();
	PlanDeTrabajoDao planDeTrabajoDao = PlanDeTrabajoDao.getInstance();
	List<LineaTablaInformes> tablaInformes;
	String mensaje;
	boolean renderedPlanDigital=false; 
	@PostConstruct
	public void init(){
		this.updateTablaInformes();
	}
	public String getDirPlan() {
		return dirPlan;
	}
	public String setDirPlan() {
		this.dirPlan = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pdf");
		this.legajo = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("legajo"));
		this.nombreAlumno = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nomAlumno")+"";
		this.psTitle = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("psTitle")+"";
		this.renderedPlanDigital=true;
		this.updateTablaInformes();
		return this.dirPlan;
	}
	public CriteriosParaFiltrarPs getCriterios() {
		return criterios;
	}
	public void setCriterios(CriteriosParaFiltrarPs criterios) {
		this.criterios = criterios;
	}
	public List<LineaTablaInformes> getTablaInformes() {
		return tablaInformes;
	}
	public void setTablaInformes(List<LineaTablaInformes> tablaInformes) {
		this.tablaInformes = tablaInformes;
	}
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
		return "se cargo informe de alumno "+Integer.toString(legajoPs)+" exitosamente";
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
	public void buttonAction(ActionEvent actionEvent) {
		this.updateTablaInformes();
		this.dirPlan=null;
		this.renderedPlanDigital=false;
    }
	public boolean isRenderedPlanDigital() {
		return renderedPlanDigital;
	}
	public void setRenderedPlanDigital(boolean renderedPlanDigital) {
		this.renderedPlanDigital = renderedPlanDigital;
	}
	public void registrarInformes(ActionEvent actionEvent) {
		this.registrarPresentacionInformes();
	}
	
	public void setDirectory(String directory) {
		this.dirPlan=directory;
	}
	
	public void editDirectory() {
		
	}
	public void updateTablaInformes() {
		CriteriosParaFiltrarPs newCriterios=new CriteriosParaFiltrarPs();
		if (this.nombreAlumno.isEmpty()) {
			this.nombreAlumno=null;
		};
		if (this.psTitle.isEmpty()) {
			this.psTitle=null;	
		}
		newCriterios.setLegajo(this.legajo);
		newCriterios.setNombreAlumno(this.nombreAlumno);
		newCriterios.setPsTitle(this.psTitle);
		this.tablaInformes = this.searchPsParaPresentarInforme(newCriterios);
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
			linea.setPs(ps);
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
	public String registrarPresentacionInforme(LineaTablaInformes linea) {
		if (linea.getFechaPresentacion()!=null) {
			InformeFinal informe = new InformeFinal();
			informe.setFechaDePresentacion(linea.getFechaPresentacion());
			PS ps=psDao.getById(linea.getPs().getId());
			informe.setPs(ps);
			inFinalDao.save(informe);
			Estado estado=estadoDAO.buscarPorNombre("Informe presentado");
			ps.setEstado(estado);
			psDao.updateEstado(ps.getId(),this.getIdEstadoInformePresentado());
			int legajoPs=ps.getAlumno().getLegajo();
			return "se cargo informe de alumno "+Integer.toString(legajoPs)+" exitosamente";
		}
		return " ";
	}
	public String registrarPresentacionInformes() {
		this.dirPlan=null;
		String salida=" ";
		for (LineaTablaInformes lineaTablaInforme: this.tablaInformes) {
			salida.concat(this.registrarPresentacionInforme(lineaTablaInforme));
		}
		this.updateTablaInformes();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bien hecho!","los informes han sido registrados exitosamente"));
		return salida;
	}
	
}