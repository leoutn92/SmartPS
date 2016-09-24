package com.smartps.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.OrganizacionDAO;
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
@ApplicationScoped
public class AltaPS {

	List<Area> areas;	
	List <Organizacion> organizaciones;
	List <TipoActividad> tiposActividades;
	
	PS ps;
	
	String areaSelec;
	String orgSelec;
	String actSelec;
	
	boolean tienePSVigente;
	boolean puedePresentarPlan;
	
	PlanDeTrabajo plan;
	
	@PostConstruct
	public void init(){
		ps= new PS();
		ps.setAlumno(new Alumno());
		plan = new PlanDeTrabajo();
		plan.setPs(ps);
		areas= AreaDao.getInstance().getAll();		
		organizaciones = OrganizacionDAO.getInstance().getAll();
		tiposActividades = TipoActividadDao.getInstance().getAll();
		ps.setEstado(new EstadoDao().buscarPorNombre("Plan Presentado"));
	}
	
	public void guardarPS(){		
		new PSDao().save(ps);
		PlanDeTrabajoDao.getInstance().save(plan);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Se guardó correctamente la Presentación del Plan") );
	}	

	public void buscarAlumno(){
		AlumnoDAO dao = new AlumnoDAO();
		ps.setAlumno( dao.buscarAlumno(ps.getAlumno().getLegajo()));		
//		if (ps.getAlumno()==null){
//			ps.setAlumno(new Alumno());
//			ps.getAlumno().setNombre("Alumno no encontrado");
//			tienePSVigente=false;
//			puedePresentarPlan=false;
//		}
//		else{
//			tienePSVigente=dao.tienePSVigente(ps.getAlumno().getLegajo());
//			puedePresentarPlan=dao.puedePresentarPlan(ps.getAlumno().getLegajo());
//		}		
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
	
	public boolean isTienePSVigente() {
		return tienePSVigente;
	}

	public void setTienePSVigente(boolean tienePSVigente) {
		this.tienePSVigente = tienePSVigente;
	}


	public boolean isPuedePresentarPlan() {
		return puedePresentarPlan;
	}

	public void setPuedePresentarPlan(boolean puedePresentarPlan) {
		this.puedePresentarPlan = puedePresentarPlan;
	}
	
}