package com.smartps.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.ReceptorEmailDao;
import com.smartps.model.ReceptorEmail;

@ManagedBean
@ViewScoped
public class ReceptoresEmailBean {

	List<ReceptorEmail> list;
	ReceptorEmailDao dao = new ReceptorEmailDao();
	ReceptorEmail recep;
	
	@PostConstruct
	public void init(){
		recep = new ReceptorEmail();
		list = dao.getAll();
	}
	
	public void guardar(){
		dao.save(recep);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se agrego correctamente la dirección"));
		this.init();
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
	
	
}
