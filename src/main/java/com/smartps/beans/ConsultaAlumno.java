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
public class ConsultaAlumno {

	private AlumnoDAO aldao = new AlumnoDAO();
	private PSDao psdao = PSDao.getInstance();
	private PlanDeTrabajoDao ptdao = PlanDeTrabajoDao.getInstance();
	private InformeFinalDao ifdao = InformeFinalDao.getInstance();

	private Alumno alumn;
	
	private int legajo;
	private String nombreAlumn;
	private int ingreso;
	
	private List<PS> pslist;
	private List<PlanDeTrabajo> ptlist;
	private List<PlanDeTrabajo> planes;
	private List<InformeFinal> iflist;
	private List<InformeFinal> informes;
	
	private PS selectedps;	
	
	private boolean panelPS;
	private boolean panelPlanInf;	
	private boolean panelAlumn;
	
	
	@PostConstruct
	public void init(){
		pslist = new ArrayList<PS>();
		ptlist = new ArrayList<PlanDeTrabajo>();
		planes = new ArrayList<PlanDeTrabajo>();
		iflist = new ArrayList<InformeFinal>();
		informes = new ArrayList<InformeFinal>();		
	}
	
	
	public void consultaByParametro(){
		
		alumn = aldao.getById(legajo);

		try{
			if (alumn.getLegajo()==legajo){
				panelAlumn = true;
				nombreAlumn = alumn.getNombre();
				ingreso = alumn.getCicloLectivo();
				
				pslist = psdao.buscarPorLegajo(legajo);
				
				if (pslist.size()==0){
					panelPS = false;
					panelPlanInf = false;
					
				} else {
					panelPS = true;
					panelPlanInf = false;					
				}	
			}
		
		}catch (Exception e){
			panelAlumn = false;
			panelPS = false;
			panelPlanInf = false;			
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
	

	
	public Alumno getAlumn() {
		return alumn;
	}

	public void setAlumn(Alumno alumn) {
		this.alumn = alumn;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombreAlumn() {
		return nombreAlumn;
	}

	public void setNombreAlumn(String nombreAlumn) {
		this.nombreAlumn = nombreAlumn;
	}

	public int getIngreso() {
		return ingreso;
	}

	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}

	public List<PS> getPslist() {
		return pslist;
	}

	public void setPslist(List<PS> pslist) {
		this.pslist = pslist;
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

	public boolean isPanelAlumn() {
		return panelAlumn;
	}

	public void setPanelAlumn(boolean panelAlumn) {
		this.panelAlumn = panelAlumn;
	}
	
}
