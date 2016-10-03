package com.smartps.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.OrganizacionDao;
import com.smartps.model.Organizacion;

@ViewScoped
@ManagedBean
public class OrgBean {
	Organizacion org;
	List<Organizacion> organizaciones;
	OrganizacionDao dao= new OrganizacionDao();

	@PostConstruct
	public void init(){
		org=new Organizacion();
		organizaciones=dao.getAll();
	}

	public void guardar(){
		dao.save(org);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se agrego correctamente la Organizacion"));
		this.init();
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public Organizacion getOrg() {
		return org;
	}

	public void setOrg(Organizacion org) {
		this.org = org;
	}
	
	

	
	
	
	
	
}
