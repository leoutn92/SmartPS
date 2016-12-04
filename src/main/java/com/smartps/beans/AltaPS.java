package com.smartps.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.OrganizacionDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.Alumno;
import com.smartps.model.Estado;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;


@ManagedBean
@ViewScoped
public class AltaPS {
	
	AreaDao daoArea = new AreaDao();
	OrganizacionDao daoOrg = new OrganizacionDao();
	TipoActividadDao daoTipoAct = new TipoActividadDao();
	AlumnoDAO daoAlumno = new AlumnoDAO();
	PSDao daoPS = new PSDao();
	PlanDeTrabajoDao daoPlan = new PlanDeTrabajoDao();
	EstadoDao daoEst = new EstadoDao();
	
	private String dirPlan;
	private boolean renderedPlanDigital;
		
	
	PS ps;
	PlanDeTrabajo plan;
	List<PlanDeTrabajo> planes;
	Alumno alum;
	
	String areaSelec;
	String orgSelec;
	String actSelec;
	int legajo;
	
	boolean noTienePSVigente;
	boolean puedePresentarPlan;
	boolean alumnoEncontrado;
	boolean aproboPS;
	private PSDao psDao;
	private PlanDeTrabajoDao planDeTrabajoDao;

	
	//resetea todos los campos y banderas del formularios. menos el legajo del tipo
	@PostConstruct
	public void init(){
		ps= new PS();
		ps.setTitulo("");
		alum = new Alumno();
		ps.setAlumno(alum);
		ps.setCicloLectivo(Calendar.getInstance().get(Calendar.YEAR));
		plan = new PlanDeTrabajo();
		plan.setPs(ps);
		plan.setFechaDePresentacion(new Date());
		planes=new ArrayList<PlanDeTrabajo>();
		noTienePSVigente=false;
		puedePresentarPlan=false;
		alumnoEncontrado=false;
		aproboPS=false;
		actSelec="";
		orgSelec="";
		areaSelec="";
		this.cambioAct();
		this.cambioArea();
		this.cambioOrg();
	}
	
	
//	este metodo busca al alumno, trae de la base la ps, si tiene alguna vigente.
	public void buscarAlumno(){
		this.init();
		alum = daoAlumno.getById(this.legajo);
		alumnoEncontrado=!(alum==null);
		if (!alumnoEncontrado){
			alum=new Alumno();
			alum.setNombre("Alumno no encontrado");
		}
		else{
			aproboPS=daoAlumno.aproboPS(alum.getLegajo());
			puedePresentarPlan=daoAlumno.puedePresentarPlan(alum.getLegajo());
			noTienePSVigente=!daoAlumno.tienePSVigente(alum.getLegajo());				
			if ( !noTienePSVigente) {
				ps=daoAlumno.getMostRecentPS(alum.getLegajo());
				plan.setPs(ps);
				planes=daoPS.getPlanes(ps.getId());
			}
			
		}
	}

// verifica que esten todos los campos completos (con alambre) y guarda la presentación.
	public void guardarPS(){		
		FacesContext context = FacesContext.getCurrentInstance();
		if (!this.faltanCampos()){
			ps.setEstado(daoEst.getById(Estado.PLAN_PRESENTADO));
			ps.setAlumno(alum);
			daoPS.save(ps);
			plan.setPs(ps);
			daoPlan.save(plan);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se guardó correctamente la Presentación del Plan") );
			this.buscarAlumno();
		}
	}


	public void cambioArea(){
		if (areaSelec!=null && !areaSelec.equals("")){
			ps.setArea(daoArea.getById(Integer.parseInt(areaSelec)));
		}
	}


	public void cambioOrg(){
		if (orgSelec!=null && !orgSelec.equals("")){
			ps.setOrganizacion(daoOrg.getById(Integer.parseInt(orgSelec)));
		}
	}


	public void cambioAct(){
		if (actSelec!=null && !actSelec.equals("")){
			ps.setTipoActividad(daoTipoAct.getById(Integer.parseInt(actSelec)));
		}
	}


	private boolean faltanCampos(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (ps.getTitulo().trim().equals("")){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe introducir un título a la práctica"));
		}
		if (ps.getCuatrimestre()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar un cuatrimestre"));
		}
		if (ps.getCicloLectivo()<2000){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar un rango valido para cilco lectivo"));
		}
		if (ps.getArea()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No ha seleccionado un area"));
		}
		
		if (ps.getTipoActividad()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No ha seleccionado un tipo de actividad"));
		}
		if (ps.getOrganizacion()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No ha seleccionado una organizacion"));
		}
		if (plan.getFechaDePresentacion()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar una fecha de presentación del plan"));
		}
		
		return ps.getTitulo().trim().equals("") || ps.getCuatrimestre()==0 ||ps.getArea()==null || ps.getOrganizacion()==null || ps.getTipoActividad()==null || ps.getAlumno()==null || plan.getFechaDePresentacion()==null || ps.getCicloLectivo()<2000;
	}

	public boolean isVisiblePanelPSNueva(){
		return alumnoEncontrado && noTienePSVigente &&!aproboPS;
	}
	
	public boolean isVisiblePanelPSVigente(){
		return alumnoEncontrado && !noTienePSVigente &&!aproboPS;
	}
	
	public boolean isVisiblePanelPlanNuevo(){
		return alumnoEncontrado && puedePresentarPlan &&!aproboPS;
	}
	
	public boolean isVisiblePanelPlanPendiente(){
		return alumnoEncontrado && !puedePresentarPlan &&!aproboPS;
	}
	
	
	
	public boolean isAlumnoEncontrado() {
		return alumnoEncontrado;
	}


	public String getActSelec() {
		return actSelec;
	}


	public String getAreaSelec() {
		return areaSelec;
	}
	
//	public List<Organizacion> getOrganizaciones() {
//		return organizaciones;
//	}
	
	
	public String updatePdf() {
		this.legajo = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("legajo"));
		int idEstadoPlanAprobado = EstadoDao.getInstance().getEstadoPlanAprobado().getId();
		PS ps = psDao.searchPs(legajo,idEstadoPlanAprobado);	
		PlanDeTrabajo plan = planDeTrabajoDao.getLastByFechaAprobadoDesaprobado(ps.getId());
		this.updatePdf(plan.getId()); 
		return "";
	}
	
	public void updatePdf(int idPlan) {
		PlanDeTrabajo plan = planDeTrabajoDao.findByID(idPlan);
		this.setDirPlan(plan.getDirDocumentoDigital());
		this.setRenderedPlanDigital(true);
	}
	
	
	
	public String getOrgSelec() {
		return orgSelec;
	}


	public PS getPs() {
		return ps;
	}


	public int getLegajo() {
		return legajo;
	}


	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}


	public Alumno getAlum() {
		return alum;
	}

	public void setAlum(Alumno alum) {
		this.alum = alum;
	}

	public PlanDeTrabajo getPlan() {
		return plan;
	}

	public void setPlan(PlanDeTrabajo plan) {
		this.plan = plan;
	}

	public void setActSelec(String actSelec) {
		this.actSelec = actSelec;
	}
	

	public void setAreaSelec(String areaSelec) {
		this.areaSelec = areaSelec;
	}

	public void setOrgSelec(String orgSelec) {
		this.orgSelec = orgSelec;
	}
	
	public List<PlanDeTrabajo> getPlanes() {
		return planes;
	}


	public void setPlanes(List<PlanDeTrabajo> planes) {
		this.planes = planes;
	}


	public void setPs(PS ps) {
		this.ps = ps;
	}
	
	
	public boolean isNoTienePSVigente() {
		return noTienePSVigente;
	}


	public boolean isPuedePresentarPlan() {
		return puedePresentarPlan;
	}


	public boolean isAproboPS() {
		return aproboPS;
	}


	public String getDirPlan() {
		return dirPlan;
	}


	public void setDirPlan(String dirPlan) {
		this.dirPlan = dirPlan;
	}

	public boolean isRenderedPlanDigital() {
		return renderedPlanDigital;
	}


	public void setRenderedPlanDigital(boolean renderedPlanDigital) {
		this.renderedPlanDigital = renderedPlanDigital;
	}
	
	
}