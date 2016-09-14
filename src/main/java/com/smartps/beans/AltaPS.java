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
	int legajo;
	Alumno alumno;
	List<Area> areas;
	Area areaSelec;
	PS ps;
	
	public Area getAreaSelec() {
		return areaSelec;
	}


	public void setAreaSelec(Area areaSelec) {
		this.areaSelec = areaSelec;
	}


	@PostConstruct
	public void init(){
		ps= new PS();
		alumno= new Alumno();
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


	public int getLegajo() {
		return legajo;
	}


	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}


	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public void buscarAlumno(){
		alumno= new AlumnoDAO().buscarAlumno(legajo);
	}
	
	
}