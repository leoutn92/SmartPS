package com.smartps.beans.desicionDelConsejoInforme;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.smartps.dao.InformeFinalDao;
import com.smartps.model.InformeFinal;
@ManagedBean
@ApplicationScoped
public class LineaInformeParaDecision {
	private int idInforme;
	private String observaciones;
	private Integer ordenanza=1;
	private Date fechaEvaluacion;
	private String nombreAlumno;
	private Integer legajo;
	private String estado;
	private String psTitle;
	private UploadedFile file;
	private InformeFinalDao iDao = new InformeFinalDao();
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getOrdenanza() {
		return ordenanza;
	}
	public void setOrdenanza(Integer ordenanza) {
		this.ordenanza = ordenanza;
	}
	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}
	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	public int getIdInforme() {
		return idInforme;
	}
	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public Integer getLegajo() {
		return legajo;
	}
	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPsTitle() {
		return psTitle;
	}
	public void setPsTitle(String psTitle) {
		this.psTitle = psTitle;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public void handleFileUpload(FileUploadEvent event) {
		this.setFile(event.getFile());
		InformeFinal informe = iDao.findById(this.getIdInforme());
		if (this.getFile()!=null) {
			try {
				informe.setFile(new SerialBlob(this.getFile().getContents()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			iDao.update(informe);
		}
		
		FacesMessage message = new FacesMessage("Bien hecho! :)", event.getFile().getFileName() + " fue cargado exitosamente.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
