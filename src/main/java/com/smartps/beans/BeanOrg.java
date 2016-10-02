package com.smartps.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.smartps.model.Organizacion;
import com.smartps.dao.OrganizacionDao;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.dao.PlanDeTrabajoDao;


@ManagedBean
public class BeanOrg {

	int id;
	Organizacion organizacion;
	PlanDeTrabajo plan;

	@PostConstruct
	public void init(){
		organizacion = new Organizacion();
		plan = new PlanDeTrabajo();
	}
	
	public void buscarOrg(){
		organizacion = OrganizacionDao.getInstance().getById(id);
		plan = PlanDeTrabajoDao.getInstance().getById(id);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public PlanDeTrabajo getPlan() {
		return plan;
	}

	public void setPlan(PlanDeTrabajo plan) {
		this.plan = plan;
	}	
	
}
