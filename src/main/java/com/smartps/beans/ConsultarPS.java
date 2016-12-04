package com.smartps.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.OrganizacionDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

@ManagedBean
@ViewScoped
public class ConsultarPS {

	PSDao daops = new PSDao();
	PlanDeTrabajoDao daoplanes = new PlanDeTrabajoDao();
	InformeFinalDao daoinformes = new InformeFinalDao();
	
	PS ps,modifPS;
	
	List<PS> pss;
	List<PlanDeTrabajo> planes;
	List<InformeFinal> informes;
	
	String areaSelec;
	String orgSelec;
	String actSelec;
	
	boolean visibleDetalles,puedeCancelarPS,puedeAprobarPS;
	
	private String dirPlan;
	private boolean renderedPlanDigital;
	private PlanDeTrabajoDao planDeTrabajoDao = PlanDeTrabajoDao.getInstance();
	private InformeFinalDao iDao= InformeFinalDao.getInstance();
	
	private String dirInformeDigital;
	private boolean renderedInformeDigital;
	
	@PostConstruct
	public void init(){
		modifPS=new PS();
		pss=daops.getAll();
		planes = new ArrayList<PlanDeTrabajo> ();
		informes = new ArrayList<InformeFinal>();
		visibleDetalles=false;
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
		if (ps!=null){
			planes = daops.getPlanes(ps.getId());
		}
	}
	
	public void actualizarInformes(){
		if (ps!=null){
			informes = daops.getInformes(ps.getId());
		}
	}
	
	public void actualizarPS(){
		if (ps!=null){
			modifPS = new PS();
			modifPS.setTitulo(ps.getTitulo());
			modifPS.setCuatrimestre(ps.getCuatrimestre());
			modifPS.setCicloLectivo(ps.getCicloLectivo());
			actSelec=""+ps.getTipoActividad().getId();
			orgSelec=""+ps.getOrganizacion().getId();
			areaSelec=""+ps.getArea().getId();
			puedeCancelarPS=ps.estaVigente();
			puedeAprobarPS=ps.puedeAprobar();
		}
	}
	
	public void guardarCambiosPS(){
		ps.setTitulo(modifPS.getTitulo());
		ps.setCuatrimestre(getModifPS().getCuatrimestre());
		ps.setCicloLectivo(modifPS.getCicloLectivo());
		ps.setArea(new AreaDao().getById(Integer.parseInt(areaSelec)));
		ps.setOrganizacion(new OrganizacionDao().getById(Integer.parseInt(orgSelec)));
		ps.setTipoActividad(new TipoActividadDao().getById(Integer.parseInt(actSelec)));
		daops.saveOrUpdate(ps);
	}
	
	public void actualizarPresentacionesPS(){
		if (ps!= null){
			modifPS=ps;
			visibleDetalles=true;
		}
		this.actualizarPlanes();
		this.actualizarInformes();
	}
	
	public void cancelarPS(){
		if (ps.estaVigente()){
			ps.setEstado(new EstadoDao().getById(11));
			daops.save(ps);
		}
			
	}
	
	public void aprobarPS(){
		if (puedeAprobarPS){
			ps.setEstado(new EstadoDao().getById(10));
			daops.save(ps);
		}		
	}
	
	public void cambioAct(){
		modifPS.setTipoActividad(new TipoActividadDao().getById(Integer.parseInt(actSelec)));
	}
	
	public void cambioOrg(){
		modifPS.setOrganizacion(new OrganizacionDao().getById(Integer.parseInt(orgSelec)));
	}
	
	public void cambioArea(){
		modifPS.setArea(new AreaDao().getById(Integer.parseInt(areaSelec)));
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


	public PS getModifPS() {
		return modifPS;
	}


	public void setModifPS(PS modifPS) {
		this.modifPS = modifPS;
	}


	public String getAreaSelec() {
		return areaSelec;
	}


	public void setAreaSelec(String areaSelec) {
		this.areaSelec = areaSelec;
	}


	public String getOrgSelec() {
		return orgSelec;
	}


	public void setOrgSelec(String orgSelec) {
		this.orgSelec = orgSelec;
	}


	public String getActSelec() {
		return actSelec;
	}


	public void setActSelec(String actSelec) {
		this.actSelec = actSelec;
	}


	public boolean isVisibleDetalles() {
		return visibleDetalles;
	}


	public void setVisibleDetalles(boolean visibleDetalles) {
		this.visibleDetalles = visibleDetalles;
	}


	public boolean isPuedeCancelarPS() {
		return puedeCancelarPS;
	}


	public void setPuedeCancelarPS(boolean puedeCancelarPS) {
		this.puedeCancelarPS = puedeCancelarPS;
	}


	public boolean isPuedeAprobarPS() {
		return puedeAprobarPS;
	}


	public void setPuedeAprobarPS(boolean puedeAprobar) {
		this.puedeAprobarPS = puedeAprobar;
	}


	public String getDirPlan() {
		return dirPlan;
	}


	public void setDirPlan(String dirPlan) {
		this.dirPlan = dirPlan;
	}


	public boolean isRenderedPlanDigital() {
		return renderedPlanDigital;
	}


	public void setRenderedPlanDigital(boolean renderedPlanDigital) {
		this.renderedPlanDigital = renderedPlanDigital;
	}
	
	public String updatePdf() {
		int idPlan = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPlan"));	
		this.updatePdf(idPlan); 
		return "";
	}
	
	public String updatePdfForInforme() {
		int idInforme = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idInforme"));	
		this.updatePdfForInforme(idInforme); 
		return "";
	}
	
	
	private void updatePdfForInforme(int idInforme) {
		InformeFinal informe = iDao.getById(idInforme);
		this.setDirInformeDigital(informe.getDirDocumentoDigital());;
		this.setRenderedInformeDigital(true);
	}


	public void updatePdf(int idPlan) {
		PlanDeTrabajo plan = planDeTrabajoDao.findByID(idPlan);
		this.setDirPlan(plan.getDirDocumentoDigital());
		this.setRenderedPlanDigital(true);
	}


	public String getDirInformeDigital() {
		return dirInformeDigital;
	}


	public void setDirInformeDigital(String dirInformeDigital) {
		this.dirInformeDigital = dirInformeDigital;
	}


	public boolean isRenderedInformeDigital() {
		return renderedInformeDigital;
	}


	public void setRenderedInformeDigital(boolean renderedInformeDigital) {
		this.renderedInformeDigital = renderedInformeDigital;
	}
	
}
