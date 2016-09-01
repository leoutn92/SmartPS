package com.smartps.model;

import java.io.Serializable;

public class PlanDeTrabajo extends DocumentoDePS implements Serializable {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
