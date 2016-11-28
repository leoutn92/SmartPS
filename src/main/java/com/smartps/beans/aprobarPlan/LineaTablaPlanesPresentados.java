package com.smartps.beans.aprobarPlan;
import java.io.File;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.SmartPSUtils;

public class LineaTablaPlanesPresentados {
	public static String path="C:/Users/User/workspace/SmartPS/src/main/webapp";
	public static String filePrefix="plan_";
	public static String fileSufix= ".pdf"; 
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
				int legajo = plan.getPs().getAlumno().getLegajo();
				int idPlan = plan.getId();
				String fileName = filePrefix+ legajo + "_" + idPlan +  fileSufix;
				File file = SmartPSUtils.saveFile(path,this.getFile(),fileName);
				plan.setDirDocumentoDigital(file.getName());
				plan.setFile(new SerialBlob(this.getFile().getContents()));
				planDeTrabajoDao.update(plan);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FacesMessage message = new FacesMessage("Bien hecho! :)", event.getFile().getFileName() + " fue cargado exitosamente.");
        FacesContext.getCurrentInstance().addMessage("panel", message);
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
