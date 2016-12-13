package com.smartps.beans.vencimientos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.EstadoDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.Estado;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

@ViewScoped
@ManagedBean
public class VencimientosPlanesBean {

	private PlanDeTrabajoDao dao = new PlanDeTrabajoDao();
	private List<LineaTablaVencimiento> planes;
	private LineaTablaVencimiento selectedPlan;
	
	
	@PostConstruct
	public void init(){
		planes = new ArrayList<LineaTablaVencimiento>();
		for (PlanDeTrabajo p : dao.getUltimosConPSPlanApobado()){
			planes.add( new LineaTablaVencimiento(p));
		}
	}
	
	public void vencerPlan(){
		PS ps = selectedPlan.getPs();
		ps.setEstado(new EstadoDao().getById(Estado.PLAN_VENCIDO));
		new PSDao().saveOrUpdate(ps);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se registro el plan como vencido"));
		this.init();
	}


	public List<LineaTablaVencimiento> getPlanes() {
		return planes;
	}


	public LineaTablaVencimiento getSelectedPlan() {
		return selectedPlan;
	}


	public void setSelectedPlan(LineaTablaVencimiento selectedPlan) {
		this.selectedPlan = selectedPlan;
	}
	
	
	
}
