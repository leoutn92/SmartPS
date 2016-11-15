package com.smartps.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

@ManagedBean
@ViewScoped
public class ConsultarPS {

	PSDao daops = new PSDao();
	PlanDeTrabajoDao daoplanes = new PlanDeTrabajoDao();
	InformeFinalDao daoinformes = new InformeFinalDao();
	
	PS ps;
	
	List<PS> pss;
	List<PlanDeTrabajo> planes;
	List<InformeFinal> informes;
	
	@PostConstruct
	public void init(){
		pss=daops.getAll();
		planes = new ArrayList<PlanDeTrabajo> ();
		informes = new ArrayList<InformeFinal>();
	}
	

	public void editarColumnaPlan(RowEditEvent event){
		PlanDeTrabajo plan = (PlanDeTrabajo )event.getObject();
		daoplanes.saveOrUpdate(plan);
		FacesMessage msg = new FacesMessage("Plan editado", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void cancelarEdicionColumna(){
		FacesMessage msg = new FacesMessage("Edicion cancelada", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);		
	}
	
	public void editarColumnaInforme (RowEditEvent event){
		InformeFinal inf = (InformeFinal) event.getObject();
		daoinformes.saveOrUpdate(inf);
		FacesMessage msg = new FacesMessage("informe editado", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public void actualizarPlanes(){
		planes = daops.getPlanes(ps.getId());
	}
	
	public void actualizarInformes(){
		informes = daops.getInformes(ps.getId());
	}

	public List<PS> getPss() {
		return pss;
	}

	public void setPss(List<PS> pss) {
		this.pss = pss;
	}

	public List<PlanDeTrabajo> getPlanes() {
		return planes;
	}

	public void setPlanes(List<PlanDeTrabajo> planes) {
		this.planes = planes;
	}

	public List<InformeFinal> getInformes() {
		return informes;
	}


	public void setInformes(List<InformeFinal> informes) {
		this.informes = informes;
	}


	public PS getPs() {
		return ps;
	}

	public void setPs(PS ps) {
		this.ps = ps;
	}
}
