package com.smartps.model;

import java.io.Serializable;

public class TipoActividad extends ClasificacionPS implements Serializable {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
