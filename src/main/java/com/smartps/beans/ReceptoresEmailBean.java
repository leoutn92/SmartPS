package com.smartps.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.ReceptorEmailDao;
import com.smartps.model.ReceptorEmail;

@ManagedBean
@ViewScoped
public class ReceptoresEmailBean implements Serializable{
	private static final long serialVersionUID = 3232298604801722965L;
	List<ReceptorEmail> list;
	ReceptorEmailDao dao = new ReceptorEmailDao();
	ReceptorEmail recep,selectedRecep;
	
	@PostConstruct
	public void init(){
//		selectedRecep = new ReceptorEmail();
		recep = new ReceptorEmail();
		list = dao.getAll();
		
	}
	
	public void guardar(){		
		dao.save(recep);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se agrego correctamente la dirección"));
		this.init();
	}

	public void remove(){
		dao.delete(selectedRecep);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se quitó al destinatario de la lista"));
		this.init();
	}
	
    public void onRowEdit(RowEditEvent event) {
    	ReceptorEmail rec = (ReceptorEmail) event.getObject();
    	dao.saveOrUpdate(rec);
    	FacesMessage msg = new FacesMessage("Destinatario editado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public List<ReceptorEmail> getList() {
		return list;
	}

	public void setList(List<ReceptorEmail> list) {
		this.list = list;
	}

	public ReceptorEmail getRecep() {
		return recep;
	}

	public void setRecep(ReceptorEmail recep) {
		this.recep = recep;
	}

	public ReceptorEmail getSelectedRecep() {
		return selectedRecep;
	}

	public void setSelectedRecep(ReceptorEmail selectedRecep) {
		this.selectedRecep = selectedRecep;
	}
	
	
}
