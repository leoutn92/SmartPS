package com.smartps.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDAO;
import com.smartps.model.Alumno;
import com.smartps.model.Area;
import com.smartps.model.PS;


@ManagedBean
public class AltaPS {

	List<Area> areas;	
	PS ps;

	@PostConstruct
	public void init(){
		ps= new PS();
		areas= new AreaDAO().getAll();		
	}

	
	public PS getPs() {
		return ps;
	}


	public void setPs(PS ps) {
		this.ps = ps;
	}


	public List<Area> getAreas() {
		return areas;
	}


	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	
	public void buscarAlumno(){
		ps.setAlumno( new AlumnoDAO().buscarAlumno(ps.getId()));
	}
	
	
}