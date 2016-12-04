package com.smartps.model;

public class DocumentoDePS extends DocumentoPresentable {
	private PS ps;
	private boolean notificadoEmail=false;

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
	
}
