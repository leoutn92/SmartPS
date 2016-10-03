package com.smartps.beans;

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
import com.smartps.model.Area;
import com.smartps.model.Organizacion;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.TipoActividad;


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
	
	List<Area> areas;	
	List <Organizacion> organizaciones;
	List <TipoActividad> tiposActividades;
	
	PS ps;
	PlanDeTrabajo plan;
	Alumno alum;
	
	String areaSelec;
	String orgSelec;
	String actSelec;
	int legajo;
	
	boolean noTienePSVigente;
	boolean puedePresentarPlan;
	boolean alumnoEncontrado;

	
	//carga las colecciones de areas actividades y org. invoca el metodo reset
	@PostConstruct
	public void init(){
		this.reset();
		areas= daoArea.getAll();
		organizaciones = daoOrg.getAll();
		tiposActividades = daoTipoAct.getAll();
	}
	
	// resetea todos los campos y banderas del formularios. menos el legajo del tipo
	private void reset(){
		ps= new PS();
		ps.setTitulo("");
		alum = new Alumno();
		ps.setAlumno(alum);
		ps.setCicloLectivo(Calendar.getInstance().get(Calendar.YEAR));
		plan = new PlanDeTrabajo();
		plan.setPs(ps);
		plan.setFechaDePresentacion(new Date());
		noTienePSVigente=false;
		puedePresentarPlan=false;
		alumnoEncontrado=false;
		actSelec="";
		orgSelec="";
		areaSelec="";
		this.cambioAct();
		this.cambioArea();
		this.cambioOrg();
	}
	
//	este metodo busca al alumno, trae de la base la ps, si tiene alguna vigente.
	public void buscarAlumno(){
		this.reset();
		alum = daoAlumno.getById(this.legajo);
		alumnoEncontrado=!(alum==null);
		if (!alumnoEncontrado){
			alum=new Alumno();
			alum.setNombre("Alumno no encontrado");
		}
		else{
			puedePresentarPlan=daoAlumno.puedePresentarPlan(alum.getLegajo());
			noTienePSVigente=!daoAlumno.tienePSVigente(alum.getLegajo());				
			if ( !noTienePSVigente) {
				ps=daoAlumno.getMostRecentPS(alum.getLegajo());
				plan.setPs(ps);
			}
			
		}
	}

// verifica que esten todos los campos completos (con alambre) y guarda la presentación.
	public void guardarPS(){		
		FacesContext context = FacesContext.getCurrentInstance();
		if (this.faltanCampos()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Faltan completar campos"));
		} else{
				ps.setEstado(daoEst.getById(1));
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
			ps.setArea(daoArea.getById(areas.get(Integer.parseInt(areaSelec)-1).getId()));
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
		
		return ps.getTitulo().equals("") || ps.getCuatrimestre()==0 ||ps.getArea()==null || ps.getOrganizacion()==null || ps.getTipoActividad()==null || ps.getAlumno()==null;
	}

	public boolean isVisiblePanelPS(){
		return alumnoEncontrado && noTienePSVigente;
	}
	
	public boolean isVisiblePanelPS2(){
		return alumnoEncontrado && !noTienePSVigente;
	}
	
	public boolean isVisiblePanelPlan(){
		return alumnoEncontrado && puedePresentarPlan;
	}
	
	public boolean isVisiblePanelPlan2(){
		return alumnoEncontrado && !puedePresentarPlan;
	}
	
	
	
	public boolean isAlumnoEncontrado() {
		return alumnoEncontrado;
	}


	public String getActSelec() {
		return actSelec;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public String getAreaSelec() {
		return areaSelec;
	}
	
	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}
	
	
	public String getOrgSelec() {
		return orgSelec;
	}


	public PS getPs() {
		return ps;
	}


	public List<TipoActividad> getTiposActividades() {
		return tiposActividades;
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
	
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public void setAreaSelec(String areaSelec) {
		this.areaSelec = areaSelec;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public void setOrgSelec(String orgSelec) {
		this.orgSelec = orgSelec;
	}
	
	public void setPs(PS ps) {
		this.ps = ps;
	}
	
	public void setTiposActividades(List<TipoActividad> tiposActividades) {
		this.tiposActividades = tiposActividades;
	}
	
	public boolean isNoTienePSVigente() {
		return noTienePSVigente;
	}


	public boolean isPuedePresentarPlan() {
		return puedePresentarPlan;
	}

	
	
	
}