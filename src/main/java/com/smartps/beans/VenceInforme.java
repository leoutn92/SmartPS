package com.smartps.beans;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.smartps.dao.PSDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.model.PS;
import com.smartps.model.InformeFinal;
import com.smartps.model.LineaVence;

@ManagedBean
@ViewScoped
public class VenceInforme {
//hdu14_informesPorVencer
	
	private PSDao psdao = PSDao.getInstance();
	private InformeFinalDao ifdao = InformeFinalDao.getInstance();
	
	private List<PS> pslist;
	private List<PS> psEstado;
	private List<InformeFinal> informes;
	private List<LineaVence> resultlist;

	private InformeFinal informe;
	private LineaVence linea;
	private boolean panelHay;
	private boolean panelNohay;


	
	@PostConstruct
	public void init(){
		pslist = new ArrayList<PS>();
		psEstado = new ArrayList<PS>();
		informes = new ArrayList<InformeFinal>();
		resultlist = new ArrayList<LineaVence>();
		verVencidos();
		
	}
	
	public void verVencidos(){

		pslist = psdao.getAll();
		
		//Filtra por estado
		for (int i=0; i<pslist.size(); i++){
			if (pslist.get(i).getEstado().getNombre().equals("Informe aprobado")){
				psEstado.add(pslist.get(i));
			}
		}

		
		for (int j=0; j<psEstado.size(); j++){
			informes = ifdao.getByIdPs(psEstado.get(j).getId());
			informe = new InformeFinal();
			informe = ultimoPlan(informes);
			
			//FechaVencimiento
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Date fechaVencimiento = informe.getFechaAprobDesaprob();
			cal.setTime(fechaVencimiento);
			cal.add(Calendar.YEAR, +1);
			fechaVencimiento = cal.getTime();
			
			
			//Setear linea
			if (informe.getFechaAprobDesaprob() != null){
				linea = new LineaVence();
				linea.setTitulo(psEstado.get(j).getTitulo());
				linea.setAlumno(psEstado.get(j).getAlumno().getNombre());
				linea.setLegajo(psEstado.get(j).getAlumno().getLegajo());
				linea.setEstado(psEstado.get(j).getEstado().getNombre());
				linea.setFechaAprobacion(informe.getFechaAprobDesaprob());
				linea.setFechaVence(fechaVencimiento);
				
				//Dia Actual y Dias Restantes
				Date diaActual = new Date();
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;				
				long diff = (fechaVencimiento.getTime() - diaActual.getTime())/MILLSECS_PER_DAY;				

				linea.setDiasRestantes(diff);
	
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
	
	
	public InformeFinal ultimoPlan(List<InformeFinal> informes){
		InformeFinal informe = new InformeFinal();
		for (int k=0; k<informes.size(); k++){
			try{
				informe = informes.get(k+1);
			}catch (Exception e) {
				informe = informes.get(k);
			}
		}
		return informe;
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
	
	public List<InformeFinal> getInformes() {
		return informes;
	}

	public void setInformes(List<InformeFinal> informes) {
		this.informes = informes;
	}

	public List<LineaVence> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<LineaVence> resultlist) {
		this.resultlist = resultlist;
	}

	public InformeFinal getInforme() {
		return informe;
	}

	public void setInforme(InformeFinal informe) {
		this.informe = informe;
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