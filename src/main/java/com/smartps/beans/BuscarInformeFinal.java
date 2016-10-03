package com.smartps.beans;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.smartps.dao.PSDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.model.PS;
import com.smartps.model.InformeFinal;
import com.smartps.model.LineaResultBusq;

@ManagedBean
@ViewScoped
public class BuscarInformeFinal {

	private PSDao psdao = PSDao.getInstance();
	private InformeFinalDao ifdao = InformeFinalDao.getInstance();
	
	private List<PS> pslist;
	private List<InformeFinal> iflist;
	private List<LineaResultBusq> linealist;
	private List<LineaResultBusq> resultlist;
	private List<LineaResultBusq> filteredLineas;
	
	private LineaResultBusq linea;

	
	@PostConstruct
	public void init(){
		pslist = new ArrayList<PS>();
		iflist = new ArrayList<InformeFinal>();
		linealist = new ArrayList<LineaResultBusq>();
		resultlist = new ArrayList<LineaResultBusq>();
//		filteredLineas = new ArrayList<LineaResultBusq>();
	}

	public void buscarIF(){
		
		pslist = psdao.getAll();
		iflist = ifdao.getAll();
		
		for (int a=0; a<iflist.size(); a++){
			for (int b=0; b<pslist.size(); b++){
				if (iflist.get(a).getPs().getId()==pslist.get(b).getId()){
					linea = new LineaResultBusq();
					linea.setTitulo(pslist.get(b).getTitulo());
					linea.setFechaDePresentacion(iflist.get(a).getFechaDePresentacion());
					linea.setFechaAprobDesaprob(iflist.get(a).getFechaAprobDesaprob());
					linea.setNroDisposicion(pslist.get(b).getNroDisposicion());
					linea.setObservaciones(iflist.get(a).getObservaciones());;
					linea.setEstado(pslist.get(b).getEstado().getNombre());
					linea.setArea(pslist.get(b).getArea().getNombre());
					linea.setOrganizacion(pslist.get(b).getOrganizacion().getNombre());
					linea.setTipoActividad(pslist.get(b).getTipoActividad().getNombre());
					linea.setLegajo(pslist.get(b).getAlumno().getLegajo());					
					linea.setAlumno(pslist.get(b).getAlumno().getNombre());
					linea.setCicloLectivo(pslist.get(b).getCicloLectivo());
					linea.setCuatrimestre(pslist.get(b).getCuatrimestre());
					linealist.add(linea);
				}
			}
		}
		
		
		//Eliminar Repetidos
		boolean repe = false;
		for (int e=0; e<linealist.size(); e++){
			if (resultlist.isEmpty()){
				resultlist.add(linealist.get(e));
			}
			for (int f=0; f<resultlist.size(); f++){
				if (linealist.get(e).getFechaDePresentacion()==resultlist.get(f).getFechaDePresentacion()){
					repe = true;
				}				
			}
			if (repe==false){
				resultlist.add(linealist.get(e));
			}
			repe = false;
		}		
		
	}

	public List<PS> getPslist() {
		return pslist;
	}

	public void setPslist(List<PS> pslist) {
		this.pslist = pslist;
	}

	public List<InformeFinal> getIflist() {
		return iflist;
	}

	public void setIflist(List<InformeFinal> iflist) {
		this.iflist = iflist;
	}

	public List<LineaResultBusq> getLinealist() {
		return linealist;
	}

	public void setLinealist(List<LineaResultBusq> linealist) {
		this.linealist = linealist;
	}

	public List<LineaResultBusq> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<LineaResultBusq> resultlist) {
		this.resultlist = resultlist;
	}

	public List<LineaResultBusq> getFilteredLineas() {
		return filteredLineas;
	}

	public void setFilteredLineas(List<LineaResultBusq> filteredLineas) {
		this.filteredLineas = filteredLineas;
	}

	public LineaResultBusq getLinea() {
		return linea;
	}

	public void setLinea(LineaResultBusq linea) {
		this.linea = linea;
	}	
	
}
