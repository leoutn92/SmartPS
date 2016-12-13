package com.smartps.beans;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.AreaDao;
import com.smartps.model.Area;

@ViewScoped
@ManagedBean
public class AreaBean implements Serializable{
	private static final long serialVersionUID = 2105810839721473798L;
	Area area,selectedArea;
	AreaDao dao= new AreaDao();
	List<Area> areas;
	
	@PostConstruct
	public void init(){
		area=new Area();
		areas=dao.getAll();
	}
	
	public void guardar(){
		dao.save(area);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se agrego correctamente el Área"));
		this.init();
	}
	
    public void onRowEdit(RowEditEvent event) {
        Area area= (Area)	event.getObject();
        dao.update(area);
    	FacesMessage msg = new FacesMessage("Area Editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void remove(){
    	try{
    		
    	
    	dao.delete(selectedArea);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito","Se elimino correctamente el Área"));
    	} catch (Exception e) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El área se encuentra actualmente ligada a una PS"));
		}
    	this.init();
    }
	
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Area getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(Area selectedArea) {
		this.selectedArea = selectedArea;
	}
	
}
