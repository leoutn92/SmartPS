package com.smartps.model;

import java.io.Serializable;

public class Estado extends ClasificacionPS implements Serializable {
	private int id;
	
	public static final int PLAN_PRESENTADO=1;
	public static final int PLAN_OBSERVADO=2;
	public static final int PLAN_APROBADO=3;
	public static final int PLAN_RECHAZADO=4;
	public static final int PLAN_VENCIDO=5;
	public static final int INFORME_PRESENTADO=6;
	public static final int INFORME_OBSERVADO=7;
	public static final int INFORME_APROBADO=8;
	public static final int INFORME_VENCIDO=9;
	public static final int PS_APROBADA=10;
	public static final int PS_CANCELADA=11;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addPs(PS ps) {
		this.getListPS().add(ps);
	}
}
