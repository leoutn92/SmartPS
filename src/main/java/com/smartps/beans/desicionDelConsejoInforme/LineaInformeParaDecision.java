package com.smartps.beans.desicionDelConsejoInforme;

import java.io.File;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.smartps.dao.InformeFinalDao;
import com.smartps.model.InformeFinal;
import com.smartps.util.SmartPSUtils;
@ManagedBean
@ViewScoped
public class LineaInformeParaDecision {
	public static String path="C:/Users/User/workspace/SmartPS/src/main/webapp";
	public static String filePrefix="informe_";
	public static String fileSufix= ".pdf";
	private int idInforme;
	private String observaciones;
	private Integer ordenanza=1;
	private Date fechaEvaluacion;
	private String nombreAlumno;
	private Integer legajo;
	private String estado;
	private String psTitle;
	private UploadedFile file;
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
		InformeFinal informe = InformeFinalDao.getInstance().findById(this.getIdInforme());
		if (this.getFile()!=null) {
			try {
				int legajo = informe.getPs().getAlumno().getLegajo();
				int idPlan = informe.getId();
				String fileName = filePrefix+ legajo + "_" + idPlan +  fileSufix;
				File file = SmartPSUtils.saveFile(path,this.getFile(),fileName);
				informe.setDirDocumentoDigital(file.getName());
				informe.setFile(new SerialBlob(this.getFile().getContents()));
				InformeFinalDao.getInstance().update(informe);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FacesMessage message = new FacesMessage("Bien hecho! :)", event.getFile().getFileName() + " fue cargado exitosamente.");
        FacesContext.getCurrentInstance().addMessage("panel", message);
    }
}
