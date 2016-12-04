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
import org.primefaces.model.StreamedContent;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int legajo;
	StreamedContent file;
	LineaTablaInformes linea;
	String nombreAlumno="";
	String psTitle="";
	CriteriosParaFiltrarPs criterios = new CriteriosParaFiltrarPs();
	Alumno alumno = new Alumno();
	PS ps = new PS();
	List<PS> pss = new ArrayList<PS>();
	Date fechaPresentacion = new Date();
	EstadoDao estadoDAO = EstadoDao.getInstance();
	PSDao psDao = PSDao.getInstance(); 
	InformeFinalDao inFinalDao = InformeFinalDao.getInstance();
	PlanDeTrabajoDao planDeTrabajoDao = PlanDeTrabajoDao.getInstance();
	List<LineaTablaInformes> tablaInformes;
	String mensaje;
	boolean renderedPlanDigital=false;
	private String dirPlan;
	@PostConstruct
	public void init(){
		this.updateTablaInformes();
		
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
		List<PS> pssConPlanAprobado = psDao.searchPs(criterios,Estado.PLAN_APROBADO);
		List<PS> pssConInformeObservado = psDao.searchPs(criterios, Estado.INFORME_OBSERVADO); 
		List<PS> pssParaPresentarInforme = pssConPlanAprobado;
		pssParaPresentarInforme.addAll(pssConInformeObservado);
		for (PS ps: pssParaPresentarInforme) {
			LineaTablaInformes linea=new LineaTablaInformes();
			linea.setPs(ps);
			linea.setLegajo(ps.getAlumno().getLegajo());
			linea.setPsTitle(ps.getTitulo());
			linea.setNombreAlumno(ps.getAlumno().getNombre());
			String nombreEstado = estadoDAO.getNameById(ps.getEstado().getId());
			linea.setEstado(nombreEstado);
			PlanDeTrabajo plan = planDeTrabajoDao.getLastByFechaAprobadoDesaprobado(ps.getId());
			if (plan!=null) {
				linea.setDirPlan(plan.getDirDocumentoDigital());
				linea.setBlob(plan.getFile());
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
			psDao.update(ps);
			psDao.updateEstado(ps.getId(),this.getIdEstadoInformePresentado());
			int legajoPs=ps.getAlumno().getLegajo();
			return "se cargo informe de alumno "+Integer.toString(legajoPs)+" exitosamente";
		}
		return " ";
	}
	public String registrarPresentacionInformes() {
		String salida=" ";
		for (LineaTablaInformes lineaTablaInforme: this.tablaInformes) {
			salida.concat(this.registrarPresentacionInforme(lineaTablaInforme));
		}
		this.updateTablaInformes();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bien hecho!","los informes han sido registrados exitosamente"));
		return salida;
	}
	
	public String updatePdf() {
		this.legajo = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("legajo"));
		int idEstadoPlanAprobado = EstadoDao.getInstance().getEstadoPlanAprobado().getId();
		PS ps = psDao.searchPs(legajo,idEstadoPlanAprobado);	
		PlanDeTrabajo plan = planDeTrabajoDao.getLastByFechaAprobadoDesaprobado(ps.getId());
		this.updatePdf(plan.getId()); 
		return "";
	}
	
	public StreamedContent getFile() {
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	public void updatePdf(int idPlan) {
		PlanDeTrabajo plan = planDeTrabajoDao.findByID(idPlan);
		this.setDirPlan(plan.getDirDocumentoDigital());
		this.setRenderedPlanDigital(true);
	}
	public String getDirPlan() {
		return dirPlan;
	}
	public void setDirPlan(String dirPlan) {
		this.dirPlan = dirPlan;
	}
	
}