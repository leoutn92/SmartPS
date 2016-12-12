package com.smartps.model;

import java.sql.Blob;
import java.util.Date;

public class DocumentoDePS extends DocumentoPresentable {
	
	private int id;
	private PS ps;
	private boolean notificadoEmail=false;

	public DocumentoDePS(){
		
	}
	
	public PS getPs() {
		return ps;
	}

	public void setPs(PS ps) {
		this.ps = ps;
	}

	public boolean isNotificadoEmail() {
		return notificadoEmail;
	}

	public void setNotificadoEmail(boolean notificadoEmail) {
		this.notificadoEmail = notificadoEmail;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
