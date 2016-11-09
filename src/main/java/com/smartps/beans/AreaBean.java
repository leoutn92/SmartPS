package com.smartps.beans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.AreaDao;
import com.smartps.model.Area;

@ViewScoped
@ManagedBean
public class AreaBean {
	Area area;
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
	
}
