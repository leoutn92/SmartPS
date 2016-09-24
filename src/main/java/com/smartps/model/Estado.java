package com.smartps.model;

import java.io.Serializable;

public class Estado extends ClasificacionPS implements Serializable {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addPs(PS ps) {
		// TODO Auto-generated method stub
		this.getListPS().add(ps);
	}
}
