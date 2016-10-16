package com.smartps.beans.aprobarPlan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.smartps.beans.registrarPresentacionInforme.CriteriosParaFiltrarPs;
import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.Alumno;
import com.smartps.model.Estado;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
@ManagedBean
@ViewScoped
public class AprobarPlan {
	static AprobarPlan bean;
	String nombreAlumno="";
	String psTitle="";
	int legajo;
	AlumnoDAO alumnoDao = new AlumnoDAO();
	PSDao psDao = PSDao.getInstance();
	EstadoDao estadoDao = EstadoDao.getInstance();
	UploadedFile file;
	LineaTablaPlanesPresentados linea;
	List<LineaTablaPlanesPresentados> tablaPlanesPresentados = new ArrayList<LineaTablaPlanesPresentados>();
	PlanDeTrabajoDao planDeTrabajoDao = PlanDeTrabajoDao.getInstance();
	public LineaTablaPlanesPresentados getLinea() {
		return linea;
	}
	public void setLinea(LineaTablaPlanesPresentados linea) {
		this.linea = linea;
	}
	public String setLinea() {
		int idPlan = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("linea"));
 		LineaTablaPlanesPresentados linea= new LineaTablaPlanesPresentados();
		for (LineaTablaPlanesPresentados l:this.tablaPlanesPresentados) {
			if (l.getIdPlan()==idPlan) {
				linea = l; 
			}
		}
		this.linea = linea;
		return "";
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	@PostConstruct
	public void init(){
		this.updateTablaPlanesPresentados();
	}
	public void buttonAction(ActionEvent actionEvent) {
		this.updateTablaPlanesPresentados();
    }
	public static AprobarPlan getInstance() {
		if (bean==null) {
			bean = new AprobarPlan();
		}
		return bean;
	}	
	public void updateTablaPlanesPresentados() {
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
		Estado estado = estadoDao.getEstadoPlanPresentado();
		Estado estadoPlanObservado = estadoDao.getEstadoPlanObservado();
		int idEstadoPresentado = estado.getId();
		int idEstadoObservado =estadoPlanObservado.getId();
		List<PS> pssObservadas =psDao.searchPs(newCriterios, idEstadoObservado);
		List<PS> pss= psDao.searchPs(newCriterios,idEstadoPresentado);
		pss.addAll(pssObservadas);
		List<LineaTablaPlanesPresentados> tabla = new ArrayList<LineaTablaPlanesPresentados>();
		for (PS p:pss) {
			LineaTablaPlanesPresentados linea = new LineaTablaPlanesPresentados(); 
			Alumno alumno = p.getAlumno();
			linea.setNombreAlumno(alumno.getNombre());
			linea.setLegajo(alumno.getLegajo());
			linea.setEstado(p.getEstado().getNombre());
			linea.setPsTitle(p.getTitulo());
			int idps = p.getId();
			linea.setIdPlan(planDeTrabajoDao.getLastByFechaAprobadoDesaprobado(idps).getId());
			tabla.add(linea);
		}
		this.tablaPlanesPresentados = tabla;
	}
	public List<LineaTablaPlanesPresentados> getTablaPlanesPresentados() {
		return tablaPlanesPresentados;
	}
	public void setTablaPlanesPresentados(List<LineaTablaPlanesPresentados> tablaPlanesPresentados) {
		this.tablaPlanesPresentados = tablaPlanesPresentados;
	}
	public PlanDeTrabajo evaluar(LineaTablaPlanesPresentados linea) {
		PlanDeTrabajo plan = planDeTrabajoDao.findByID(linea.getIdPlan());
		try {
			File dest=new File(this.getFile().getFileName());
			FileUtils.copyInputStreamToFile(this.getFile().getInputstream(),dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		plan.setFechaAprobDesaprob(linea.getFechaEvaluacion());
		plan.setObservaciones(linea.getObservaciones());
		plan.setOrdenanza(linea.getOrdenanza());
		planDeTrabajoDao.update(plan);
		return plan;
	}
	public void aprobar() {
		this.evaluar(linea.getIdPlan());
		this.aprobar(linea.getIdPlan());
		this.updateTablaPlanesPresentados();
	}
	public void desaprobar(ActionEvent event) {
		int idPlan =(int) event.getComponent().getAttributes().get("linea");
		this.evaluar(idPlan);
		this.desaprobar(idPlan);
		this.updateTablaPlanesPresentados();
	}
	public void aprobar(ActionEvent event) {
		int idPlan =(int) event.getComponent().getAttributes().get("linea");
		this.evaluar(idPlan);
		this.aprobar(idPlan);
		FacesMessage message = new FacesMessage("Bien hecho! :)", "este plan es ahora un plan aprobado");
        FacesContext.getCurrentInstance().addMessage(null, message);
		this.updateTablaPlanesPresentados();
	}
	public void aprobar(int idPlan) {
		PlanDeTrabajo plan = this.evaluar(idPlan);
		Estado planAprobado = estadoDao.getEstadoPlanAprobado();
		PS ps = psDao.findById(plan.getPs().getId()); 
		ps.setEstado(planAprobado);
		psDao.update(ps);
	}
	public void handleFileUpload(FileUploadEvent event) {
		this.setFile(event.getFile());
        FacesMessage message = new FacesMessage("Bien hecho! :)", event.getFile().getFileName() + " fue cargado exitosamente.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void desaprobar(int idPlan) {
		PlanDeTrabajo plan = this.evaluar(idPlan);
		Estado planObserbado = estadoDao.getEstadoPlanObservado();
		PS ps = psDao.findById(plan.getPs().getId()); 
		ps.setEstado(planObserbado);
		psDao.update(ps);
		FacesMessage message = new FacesMessage("Bien hecho! :)", "este plan es ahora un plan observado");
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	private PlanDeTrabajo evaluar(int idPlan) {
		// TODO Auto-generated method stub
		LineaTablaPlanesPresentados linea= new LineaTablaPlanesPresentados();
		for (LineaTablaPlanesPresentados l:this.tablaPlanesPresentados) {
			if (l.getIdPlan()==idPlan) {
				linea = l; 
			}
		}
		PlanDeTrabajo plan = planDeTrabajoDao.findByID(linea.getIdPlan());	
		plan.setFechaAprobDesaprob(linea.getFechaEvaluacion());
		plan.setObservaciones(linea.getObservaciones());
		plan.setOrdenanza(linea.getOrdenanza());
		planDeTrabajoDao.update(plan);
		return plan; 
	}
	public void aprobar(LineaTablaPlanesPresentados linea) {
		PlanDeTrabajo plan = this.evaluar(linea); 
		Estado planAprobado = estadoDao.getEstadoPlanAprobado();
		PS ps = psDao.findById(plan.getPs().getId()); 
		ps.setEstado(planAprobado);
		psDao.update(ps);
		this.updateTablaPlanesPresentados();
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

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}


	public void desaprobar(LineaTablaPlanesPresentados linea) {
		// TODO Auto-generated method stub
		PlanDeTrabajo plan = this.evaluar(linea);
		Estado planDesaprobado = estadoDao.getEstadoPlanObservado();
		PS ps = psDao.findById(plan.getPs().getId()); 
		ps.setEstado(planDesaprobado);
		psDao.update(ps);
		this.updateTablaPlanesPresentados();
	}
}
