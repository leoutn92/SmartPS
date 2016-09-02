package com.smartps.model;

import java.util.HashSet;
import java.util.Set;

public class ClasificacionPS extends Clasificacion {
	private Set<PS> listPS = new HashSet<PS>(0);

	public Set<PS> getListPS() {
		return listPS;
	}

	public void setListPS(Set<PS> listPS) {
		this.listPS = listPS;
	}
}
