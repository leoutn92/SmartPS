package com.smartps.model;

import java.io.Serializable;

public class InformeFinal extends DocumentoDePS implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
