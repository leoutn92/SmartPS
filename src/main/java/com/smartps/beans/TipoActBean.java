package com.smartps.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.TipoActividadDao;
import com.smartps.model.TipoActividad;

@ViewScoped
@ManagedBean
public class TipoActBean {
	TipoActividad tipoAct;
	TipoActividadDao dao= new TipoActividadDao();
	List<TipoActividad> tiposAct;
	
	@PostConstruct
	public void init(){
		tipoAct= new TipoActividad();
		tiposAct= dao.getAll();
	}
	
	public void guardar(){
		dao.save(tipoAct);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se agrego correctamente el Tipo de Actividad"));
		this.init();
		
	}
	
	public TipoActividad getTipoAct() {
		return tipoAct;
	}
	public void setTipoAct(TipoActividad tipoAct) {
		this.tipoAct = tipoAct;
	}
	public List<TipoActividad> getTiposAct() {
		return tiposAct;
	}
	public void setTiposAct(List<TipoActividad> tiposAct) {
		this.tiposAct = tiposAct;
	}
	
	
}
