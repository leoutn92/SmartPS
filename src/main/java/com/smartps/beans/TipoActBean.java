package com.smartps.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.TipoActividadDao;
import com.smartps.model.TipoActividad;

@ViewScoped
@ManagedBean
public class TipoActBean implements Serializable {
	private static final long serialVersionUID = 2814408433177452110L;
	TipoActividad tipoAct,selectedTipoAct;
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
	
	
    public void onRowEdit(RowEditEvent event) {
        TipoActividad act= (TipoActividad)	event.getObject();
        dao.update(act);
    	FacesMessage msg = new FacesMessage("Tipo de actividad Editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void remove(){
    	try{
    		
    	
    	dao.delete(selectedTipoAct);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se elimino correctamente el Tipo de Actividad"));
    	} catch (Exception e) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","La actividad se encuentra actualmente ligada a una PS"));
		}
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

	public TipoActividad getSelectedTipoAct() {
		return selectedTipoAct;
	}

	public void setSelectedTipoAct(TipoActividad selectedTipoAct) {
		this.selectedTipoAct = selectedTipoAct;
	}
	
	
}
