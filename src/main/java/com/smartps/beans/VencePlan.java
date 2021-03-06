package com.smartps.beans;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.smartps.dao.EstadoDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.LineaVence;

@ManagedBean
@ViewScoped
public class VencePlan {
//hdu13_planesPorVencer
	
	private PSDao psdao = new PSDao();
	private PlanDeTrabajoDao ptdao = new PlanDeTrabajoDao();
	
	private List<PS> pslist;
	private List<PS> psEstado;
	private List<PlanDeTrabajo> planes;
	private List<LineaVence> resultlist;

	private PlanDeTrabajo plan;
	private LineaVence linea;
	private boolean panelHay;
	private boolean panelNohay;
	
	private PS selectedPS;
	
	public void vencerPlan(){
		selectedPS.setEstado(new EstadoDao().getById(5));
		psdao.saveOrUpdate(selectedPS);
	}


	
	public PS getSelectedPS() {
		return selectedPS;
	}

	public void setSelectedPS(PS selectedPS) {
		this.selectedPS = selectedPS;
	}

	@PostConstruct
	public void init(){
		pslist = new ArrayList<PS>();
		psEstado = new ArrayList<PS>();
		planes = new ArrayList<PlanDeTrabajo>();
		resultlist = new ArrayList<LineaVence>();
		verVencidos();
		
	}
	
	public void verVencidos(){

		pslist = psdao.getAll();
		
		//Filtra por estado
		for (int i=0; i<pslist.size(); i++){
			if (
					(pslist.get(i).getEstado().getNombre().equals("Plan aprobado"))
					||
					(pslist.get(i).getEstado().getNombre().equals("Informe presentado"))
					||
					(pslist.get(i).getEstado().getNombre().equals("Informe observado"))
				){
				psEstado.add(pslist.get(i));
				
			}
		}

		
		for (int j=0; j<psEstado.size(); j++){
			planes = ptdao.getByIdPs(psEstado.get(j).getId());
			plan = new PlanDeTrabajo();
			plan = ultimoPlan(planes);
			
			//FechaVencimiento
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Date fechaVencimiento = plan.getFechaAprobDesaprob();
			cal.setTime(fechaVencimiento);
			cal.add(Calendar.YEAR, +1);
			fechaVencimiento = cal.getTime();
			
			
			//Setear linea
			if (plan.getFechaAprobDesaprob() != null){
				linea = new LineaVence();
				linea.setTitulo(psEstado.get(j).getTitulo());
				linea.setAlumno(psEstado.get(j).getAlumno().getNombre());
				linea.setLegajo(psEstado.get(j).getAlumno().getLegajo());
				linea.setEstado(psEstado.get(j).getEstado().getNombre());
				linea.setFechaAprobacion(plan.getFechaAprobDesaprob());
				linea.setPs(plan.getPs());
				linea.setFechaVence(fechaVencimiento);
				
				//Dia Actual y Dias Restantes
				Date diaActual = new Date();
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;				
				long diff = (fechaVencimiento.getTime() - diaActual.getTime())/MILLSECS_PER_DAY;				

				linea.setDiasRestantes((int)diff);
	
				//Falta menos de un mes
				if (diff<32){
					resultlist.add(linea);
				}
			}
			
		}
		
		if (resultlist.isEmpty()){
			panelHay = false;
			panelNohay = true;
		}else{
			panelHay = true;
			panelNohay = false;
		}
		
		
	}
	
	
	public PlanDeTrabajo ultimoPlan(List<PlanDeTrabajo> planes){
		PlanDeTrabajo plan = new PlanDeTrabajo();
		for (int k=0; k<planes.size(); k++){
			try{
				plan = planes.get(k+1);
			}catch (Exception e) {
				plan = planes.get(k);
			}
		}
		return plan;
	}


	
	public List<PS> getPslist() {
		return pslist;
	}

	public void setPslist(List<PS> pslist) {
		this.pslist = pslist;
	}

	public List<PS> getPsEstado() {
		return psEstado;
	}

	public void setPsEstado(List<PS> psEstado) {
		this.psEstado = psEstado;
	}

	public List<PlanDeTrabajo> getPlanes() {
		return planes;
	}

	public void setPlanes(List<PlanDeTrabajo> planes) {
		this.planes = planes;
	}

	public List<LineaVence> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<LineaVence> resultlist) {
		this.resultlist = resultlist;
	}

	public PlanDeTrabajo getPlan() {
		return plan;
	}

	public void setPlan(PlanDeTrabajo plan) {
		this.plan = plan;
	}

	public LineaVence getLinea() {
		return linea;
	}

	public void setLinea(LineaVence linea) {
		this.linea = linea;
	}

	public boolean isPanelHay() {
		return panelHay;
	}

	public void setPanelHay(boolean panelHay) {
		this.panelHay = panelHay;
	}

	public boolean isPanelNohay() {
		return panelNohay;
	}

	public void setPanelNohay(boolean panelNohay) {
		this.panelNohay = panelNohay;
	}
	
}