package com.smartps.beans;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
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
	
	@PostConstruct
	public void init(){
		ps= new PS();
		alum = new Alumno();
		ps.setAlumno(alum);
		plan = new PlanDeTrabajo();
		plan.setPs(ps);
		plan.setFechaDePresentacion(new Date());
		areas= daoArea.getAll();
		organizaciones = daoOrg.getAll();
		tiposActividades = daoTipoAct.getAll();
		noTienePSVigente=false;
		puedePresentarPlan=false;
		alumnoEncontrado=false;
		
	}
	

//
//	public void guardarPS(){		
//		ps.setArea(AreaDao.getInstance().buscarArea(Integer.parseInt(areaSelec)));
//		ps.setTipoActividad(TipoActividadDao.getInstance().findById(Integer.parseInt(actSelec)));
//		ps.setOrganizacion(OrganizacionDao.getInstance().findByID(Integer.parseInt(orgSelec)));
//		ps.setEstado(EstadoDao.getInstance().buscarPorNombre("Plan presentado"));
//		PSDao.getInstance().save(ps);
//		PlanDeTrabajoDao.getInstance().save(plan);
//        
//		
//		FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Se guardó correctamente la Presentación del Plan") );        
//	}	

//	public void buscarAlumno(){
//		AlumnoDAO dao = AlumnoDAO.getInstance();
//		ps.setAlumno( dao.buscarAlumno(ps.getAlumno().getLegajo()));		
//		if (ps.getAlumno()==null){
//			ps.setAlumno(new Alumno());
//			ps.getAlumno().setNombre("Alumno no encontrado");
//			noTienePSVigente=false;
//			puedePresentarPlan=false;
//		}
//		else{
//			noTienePSVigente=!dao.tienePSVigente(ps.getAlumno().getLegajo());
//			puedePresentarPlan=dao.puedePresentarPlan(ps.getAlumno().getLegajo());
//		}		
//	}
	
	public void buscarAlumno(){
// en este metodo se acualizan todas las banderas alumno y ps vigente, de existir
			alum = daoAlumno.getById(this.legajo);
			alumnoEncontrado=!(alum==null);
			if (!alumnoEncontrado){
				alum=new Alumno();
				alum.setNombre("Alumno no encontrado");
				noTienePSVigente=false;
				puedePresentarPlan=false;
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


	public void guardarPS(){		
		FacesContext context = FacesContext.getCurrentInstance();
		if (this.faltanCampos()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Faltan completar campos"));
		} else{
				
				if (noTienePSVigente){
					ps.setArea(AreaDao.getInstance().getById(Integer.parseInt(areaSelec)));
					ps.setTipoActividad(TipoActividadDao.getInstance().getById(Integer.parseInt(actSelec)));
					ps.setOrganizacion(OrganizacionDao.getInstance().getById(Integer.parseInt(orgSelec)));
					ps.setEstado(EstadoDao.getInstance().buscarPorNombre("Plan presentado"));
					
				}
				PSDao.getInstance().save(ps);
				PlanDeTrabajoDao.getInstance().save(plan);
		
			
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se guardó correctamente la Presentación del Plan") );
			}
	}


	public Alumno getAlum() {
		return alum;
	}


	public void setAlum(Alumno alum) {
		this.alum = alum;
	}


	public void cambioArea(){
		if (areaSelec!=null && !areaSelec.equals("")){
			ps.setArea(areas.get(Integer.parseInt(areaSelec)-1));
		}
	}


	public void cambioOrg(){
		if (orgSelec!=null && !orgSelec.equals("")){
			ps.setOrganizacion(organizaciones.get(Integer.parseInt(orgSelec)-1));
		}
	}


	public void cambioAct(){
		if (actSelec!=null && !actSelec.equals("")){
			ps.setTipoActividad(tiposActividades.get(Integer.parseInt(actSelec)-1));
		}
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
	
	
	
	private boolean faltanCampos(){
		return false;
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