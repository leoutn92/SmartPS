package com.smartps.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.AlumnoDAO;
import com.smartps.model.Alumno;

@ManagedBean
@ViewScoped
public class AlumnosBean {
	private Alumno alumno;
	private List<Alumno> alumnos;
	private AlumnoDAO dao=new AlumnoDAO();
	
	@PostConstruct
	protected void init(){
		alumno= new Alumno();
		alumnos= dao.getAll();
	}

	public void guardarAlumno(){
		if (dao.getById(alumno.getLegajo())==null){
			dao.save(alumno);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "El alumno se agregó correctamente", ""));
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un alumno con el mismo legajo", ""));
		}
		this.init();
	}
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
}
