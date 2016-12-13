package com.smartps.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.OrganizacionDao;
import com.smartps.model.Organizacion;

@ViewScoped
@ManagedBean
public class OrgBean implements Serializable{
	private static final long serialVersionUID = 2175962617430107104L;
	Organizacion org,selectedOrg;
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
	
    public void onRowEdit(RowEditEvent event) {
        Organizacion org= (Organizacion)	event.getObject();
        dao.update(org);
    	FacesMessage msg = new FacesMessage("Organizacion Editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void remove(){
    	try{
    		
    	
    	dao.delete(selectedOrg);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se elimino correctamente La organizacion"));
    	} catch (Exception e) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","La organizacion se encuentra actualmente ligada a una PS"));
		}
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

	public Organizacion getSelectedOrg() {
		return selectedOrg;
	}

	public void setSelectedOrg(Organizacion selectedOrg) {
		this.selectedOrg = selectedOrg;
	}
	
	

	
	
	
	
	
}
