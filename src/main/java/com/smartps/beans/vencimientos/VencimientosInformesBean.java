package com.smartps.beans.vencimientos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;


@ViewScoped
@ManagedBean
public class VencimientosInformesBean implements Serializable{
	private static final long serialVersionUID = 1807423449557327344L;
	private InformeFinalDao dao = new InformeFinalDao();
	private List<LineaTablaVencimiento> informes;
	private LineaTablaVencimiento selectedInforme;
	
	
	@PostConstruct
	public void init(){
		informes = new ArrayList<LineaTablaVencimiento>();
		for (InformeFinal p : dao.getUltimosConPSInformeApobado()){
			informes.add( new LineaTablaVencimiento(p));
		}
	}
	
	public void vencerInforme(){
		PS ps = selectedInforme.getPs();
		ps.setEstado(new EstadoDao().getById(Estado.INFORME_VENCIDO));
		new PSDao().saveOrUpdate(ps);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se registro el informe como vencido"));
		this.init();
	}


	public List<LineaTablaVencimiento> getInformes() {
		return informes;
	}


	public LineaTablaVencimiento getSelectedInforme() {
		return selectedInforme;
	}


	public void setSelectedInforme(LineaTablaVencimiento selectedPlan) {
		this.selectedInforme = selectedPlan;
	}
	
	
	
}
