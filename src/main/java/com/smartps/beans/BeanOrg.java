package com.smartps.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.smartps.model.Organizacion;
import com.smartps.dao.OrganizacionDAO;


@ManagedBean
public class BeanOrg {

	int id;
	Organizacion organizacion;

	@PostConstruct
	public void init(){
		organizacion = new Organizacion();
	}
	
	public void buscarOrg(){
		organizacion = new OrganizacionDAO().findByID(id);
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
	
}
