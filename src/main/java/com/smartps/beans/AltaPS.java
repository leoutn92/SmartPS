package com.smartps.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.smartps.dao.AlumnoDAO;
import com.smartps.model.Alumno;


@ManagedBean
public class AltaPS {
	String legajo;
	Alumno alumno;
	
	@PostConstruct
	public void init(){
		alumno= new Alumno();
		alumno.setNombre("Lucas");
	}

	
	public String getLegajo() {
		return legajo;
	}


	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}


	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public void buscarAlumno(){
		alumno = new AlumnoDAO().buscarAlumno(legajo);
		System.out.println(alumno.getNombre());
	}
	
	
}