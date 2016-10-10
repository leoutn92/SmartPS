package com.smartps.beans.aprobarPlan;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.PlanDeTrabajo;

@ManagedBean
@ApplicationScoped
public class LineaTablaPlanesPresentados {
	private String nombreAlumno;
	private String observaciones;
	private Date fechaEvaluacion;
	private int legajo;
	private String estado;
	private int idPlan;
	private UploadedFile file;
	private String psTitle;
	private int ordenanza;
	private String dirDocumentoDigital;
	PlanDeTrabajoDao planDeTrabajoDao = PlanDeTrabajoDao.getInstance();
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombre) {
		this.nombreAlumno = nombre;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		this.setFile(event.getFile());
		PlanDeTrabajo plan = planDeTrabajoDao.findByID(this.getIdPlan());
		if (this.getFile()!=null) {
			try {
				plan.setFile(new SerialBlob(this.getFile().getContents()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			planDeTrabajoDao.update(plan);
		}
		
		FacesMessage message = new FacesMessage("Bien hecho! :)", event.getFile().getFileName() + " fue cargado exitosamente.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	

	public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

	public String getPsTitle() {
		return psTitle;
	}

	public void setPsTitle(String psTitle) {
		this.psTitle = psTitle;
	}

	public int getOrdenanza() {
		return ordenanza;
	}

	public void setOrdenanza(int ordenanza) {
		this.ordenanza = ordenanza;
	}

	public String getDirDocumentoDigital() {
		return dirDocumentoDigital;
	}

	public void setDirDocumentoDigital(String dirDocumentoDigital) {
		this.dirDocumentoDigital = dirDocumentoDigital;
	}


}
