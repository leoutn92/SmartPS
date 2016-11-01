package com.smartps.beans;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.model.Alumno;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.InformeFinal;


@ManagedBean
@ViewScoped
public class ConsultaIngreso {

	private AlumnoDAO aldao = new AlumnoDAO();
	private PSDao psdao = PSDao.getInstance();
	private PlanDeTrabajoDao ptdao = PlanDeTrabajoDao.getInstance();
	private InformeFinalDao ifdao = InformeFinalDao.getInstance();	

	private List<Alumno> alumnlist;
	private List<PS> pslist;
	private List<PS> pss;
	private List<PlanDeTrabajo> ptlist;
	private List<PlanDeTrabajo> planes;
	private List<InformeFinal> iflist;
	private List<InformeFinal> informes;
	
	private int ingreso;
	private PS selectedps;
	
	private boolean panelPS;
	private boolean panelPlanInf;
	private boolean panelNo;
	
	
	@PostConstruct
	public void init(){
		alumnlist = new ArrayList<Alumno>();
		pslist = new ArrayList<PS>();
		pss = new ArrayList<PS>();
		ptlist = new ArrayList<PlanDeTrabajo>();
		planes = new ArrayList<PlanDeTrabajo>();
		iflist = new ArrayList<InformeFinal>();
		informes = new ArrayList<InformeFinal>();		
	}
	
	
	public void consultaByParametro(){
		
		alumnlist = aldao.findByIngreso(ingreso);
		pss = new ArrayList<PS>();
		
		if (alumnlist.isEmpty()){
			panelPS = false;
			panelPlanInf = false;
			panelNo = true;
		}else{

			panelPS = true;
			panelPlanInf = false;
			panelNo = false;
			
			for (int x=0; x<alumnlist.size(); x++){
				pslist = psdao.buscarPorLegajo(alumnlist.get(x).getLegajo());
				pss.addAll(pslist);
			}
			
		}

	}
	
	
	public void verPlanInf(){
		
		try{
			if (selectedps.getTitulo().equals("")){
				panelPlanInf = false;
			}else{
				panelPlanInf = true;
				
				ptlist = ptdao.getByIdPs(selectedps.getId());
				planes = new ArrayList<PlanDeTrabajo>();
				for (int j=0; j<ptlist.size(); j++){
					planes.add(ptlist.get(j));
				}
				
				iflist = ifdao.getByIdPs(selectedps.getId());
				informes = new ArrayList<InformeFinal>();
				for (int j=0; j<iflist.size(); j++){
					informes.add(iflist.get(j));
				}
				
			}
			
		}catch (Exception e) {
			panelPlanInf = false;
		}
		
	}
	


	public List<Alumno> getAlumnlist() {
		return alumnlist;
	}

	public void setAlumnlist(List<Alumno> alumnlist) {
		this.alumnlist = alumnlist;
	}
	
	public List<PS> getPslist() {
		return pslist;
	}

	public void setPslist(List<PS> pslist) {
		this.pslist = pslist;
	}

	public List<PS> getPss() {
		return pss;
	}

	public void setPss(List<PS> pss) {
		this.pss = pss;
	}

	public List<PlanDeTrabajo> getPtlist() {
		return ptlist;
	}

	public void setPtlist(List<PlanDeTrabajo> ptlist) {
		this.ptlist = ptlist;
	}

	public List<PlanDeTrabajo> getPlanes() {
		return planes;
	}

	public void setPlanes(List<PlanDeTrabajo> planes) {
		this.planes = planes;
	}

	public List<InformeFinal> getIflist() {
		return iflist;
	}

	public void setIflist(List<InformeFinal> iflist) {
		this.iflist = iflist;
	}

	public List<InformeFinal> getInformes() {
		return informes;
	}

	public void setInformes(List<InformeFinal> informes) {
		this.informes = informes;
	}

	public int getIngreso() {
		return ingreso;
	}

	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}

	public PS getSelectedps() {
		return selectedps;
	}

	public void setSelectedps(PS selectedps) {
		this.selectedps = selectedps;
	}

	public boolean isPanelPS() {
		return panelPS;
	}

	public void setPanelPS(boolean panelPS) {
		this.panelPS = panelPS;
	}

	public boolean isPanelPlanInf() {
		return panelPlanInf;
	}

	public void setPanelPlanInf(boolean panelPlanInf) {
		this.panelPlanInf = panelPlanInf;
	}

	public boolean isPanelNo() {
		return panelNo;
	}

	public void setPanelNo(boolean panelNo) {
		this.panelNo = panelNo;
	}

}
