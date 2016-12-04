package com.smartps.beans.aprobarPlan;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;
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
import com.smartps.util.SmartPSUtils;

@ManagedBean
@ViewScoped
public class AprobarPlan {
	static AprobarPlan bean;
	String nombreAlumno = "";
	String psTitle = "";
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
		int idPlan = Integer
				.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("linea"));
		LineaTablaPlanesPresentados linea = new LineaTablaPlanesPresentados();
		for (LineaTablaPlanesPresentados l : this.tablaPlanesPresentados) {
			if (l.getIdPlan() == idPlan) {
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
	public void init() {
		this.updateTablaPlanesPresentados();
	}

	public void buttonAction(ActionEvent actionEvent) {
		this.updateTablaPlanesPresentados();
	}

	public static AprobarPlan getInstance() {
		if (bean == null) {
			bean = new AprobarPlan();
		}
		return bean;
	}

	public void updateTablaPlanesPresentados() {
		// TODO Auto-generated method stub
		CriteriosParaFiltrarPs newCriterios = new CriteriosParaFiltrarPs();
		if (this.nombreAlumno != null) {
			if (this.nombreAlumno.isEmpty()) {
				this.nombreAlumno = null;
			}
			;
		}
		if (this.psTitle != null) {
			if (this.psTitle.isEmpty()) {
				this.psTitle = null;
			}
		}
		newCriterios.setLegajo(this.legajo);
		newCriterios.setNombreAlumno(this.nombreAlumno);
		newCriterios.setPsTitle(this.psTitle);
		Estado estado = estadoDao.getEstadoPlanPresentado();
		int idEstadoPresentado = estado.getId();
		List<PS> pss = psDao.searchPs(newCriterios, idEstadoPresentado);
		List<LineaTablaPlanesPresentados> tabla = new ArrayList<LineaTablaPlanesPresentados>();
		for (PS p : pss) {
			LineaTablaPlanesPresentados linea = new LineaTablaPlanesPresentados();
			Alumno alumno = p.getAlumno();
			linea.setNombreAlumno(alumno.getNombre());
			linea.setLegajo(alumno.getLegajo());
			linea.setEstado(estado.getNombre());
			linea.setPsTitle(p.getTitulo());
			int idps = p.getId();
			linea.setIdPlan(planDeTrabajoDao.getWithoutFechaEvaluacion(idps).getId());
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

	public void desaprobar(ActionEvent event) {
		int idPlan = (int) event.getComponent().getAttributes().get("linea");
		Estado estado = EstadoDao.getInstance().getEstadoPlanObservado();
		evaluar(idPlan, estado);
	}

	private String getMessage(LineaTablaPlanesPresentados linea) {
		if (tieneErrores(linea)) {
			return "deben completarse todos los campos";
		}
		return "Bien hecho se registro la decision del consejo respecto del plan";
	}

	private LineaTablaPlanesPresentados buscarLineaByIdPlan(int idPlan) {
		// TODO Auto-generated method stub
		LineaTablaPlanesPresentados linea = null;
		for (LineaTablaPlanesPresentados l : this.tablaPlanesPresentados) {
			if (l.getIdPlan() == idPlan) {
				linea = l;
			}
		}
		return linea;
	}

	public void aprobar(ActionEvent event) {
		int idPlan = (int) event.getComponent().getAttributes().get("linea");
		Estado estado = EstadoDao.getInstance().getEstadoPlanAprobado();
		evaluar(idPlan, estado);
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.setFile(event.getFile());
		FacesMessage message = new FacesMessage("Bien hecho! :)",
				event.getFile().getFileName() + " fue cargado exitosamente.");
		FacesContext.getCurrentInstance().addMessage("panel", message);
	}

	public boolean tieneErrores(LineaTablaPlanesPresentados linea) {
		return (((linea == null)) || SmartPSUtils.isNullOrEmpty(linea.getObservaciones())
				|| (linea.getFechaEvaluacion() == null));
	}

	private void evaluar(int idPlan, Estado estado) {
		LineaTablaPlanesPresentados linea = buscarLineaByIdPlan(idPlan);
		if (!tieneErrores(linea)) {
			PlanDeTrabajo plan = planDeTrabajoDao.findByID(linea.getIdPlan());
			plan.setFechaAprobDesaprob(linea.getFechaEvaluacion());
			plan.setObservaciones(linea.getObservaciones());
			plan.setOrdenanza(linea.getOrdenanza());
			planDeTrabajoDao.update(plan);
			plan = planDeTrabajoDao.getById(idPlan);
			PS ps = psDao.findById(plan.getPs().getId());
			ps.setEstado(estado);
			psDao.update(ps);
			this.updateTablaPlanesPresentados();
			RequestContext.getCurrentInstance().showMessageInDialog(
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Bien hecho!", "El plan fue evaluado"));
			RequestContext.getCurrentInstance().addCallbackParam("tieneErrores", tieneErrores(linea));
		} else {
			String message = getMessage(linea);
			FacesContext.getCurrentInstance().addMessage("panel",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
			RequestContext.getCurrentInstance().addCallbackParam("tieneErrores", tieneErrores(linea));
		}
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
}
