package com.smartps.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.OrganizacionDAO;
import com.smartps.dao.PSDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.Area;
import com.smartps.model.Organizacion;
import com.smartps.model.PS;
import com.smartps.model.TipoActividad;


@ManagedBean
public class AltaPS {

	List<Area> areas;	
	List <Organizacion> organizaciones;
	List <TipoActividad> tiposActividades;
	
	PS ps;
	
	String areaSelec;
	String orgSelec;
	String actSelec;
	
	public void guardarPS(){
		new PSDao().save(ps);
	}


	public void buscarAlumno(){
		ps.setAlumno( new AlumnoDAO().buscarAlumno(ps.getId()));
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
			System.out.println(ps.getTipoActividad().getDescripsion());
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


	@PostConstruct
	public void init(){
		ps= new PS();
		areas= new AreaDao().getAll();		
		organizaciones = OrganizacionDAO.getInstance().getAll();
		tiposActividades = TipoActividadDao.getInstance().getAll();
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
	
}