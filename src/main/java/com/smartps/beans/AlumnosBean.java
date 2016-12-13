package com.smartps.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.AlumnoDAO;
import com.smartps.model.Alumno;

@ManagedBean
@ViewScoped
public class AlumnosBean implements Serializable {
	private static final long serialVersionUID = -2134038382989082206L;
	private Alumno alumno,selectedAlumn;
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
	
    public void onRowEdit(RowEditEvent event) {
        Alumno alu= (Alumno)	event.getObject();
        dao.update(alu);
    	FacesMessage msg = new FacesMessage("Alumno editado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void remove(){
    	try{
	    	dao.delete(selectedAlumn);
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se elimino correctamente al alumno"));
    	} catch (Exception e) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El tiene ps cargadas. No se puede eliminar"));
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

	public Alumno getSelectedAlumn() {
		return selectedAlumn;
	}

	public void setSelectedAlumn(Alumno selectedAlumn) {
		this.selectedAlumn = selectedAlumn;
	}
	
	
}
