package com.smartps.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.smartps.dao.AlumnoDAO;
import com.smartps.model.Alumno;


@ManagedBean
public class AltaPS {
	int legajo;
	Alumno alumno;
	
	@PostConstruct
	public void init(){
		alumno= new Alumno();
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