package com.smartps.beans;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.faces.bean.ManagedBean;

import com.smartps.dao.PSDAO;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.LineaDeReporte;

@ManagedBean
public class GenerarReportePG {
//GenerarReportePG = GenerarReportePlanesGeneral_hdu6 
	
	private PSDAO dao = new PSDAO();
	
	private List<PS> pslist;
	private List<LineaDeReporte> linealist;
	
	
	private int cicloLectivo;
	private int cuatrimestre;
	private Date desde;
	private Date hasta;
	
	private LineaDeReporte linea;

	//contadores
	private int cPP;
	private int cPA;
	private int cPD;
	private int cPV;
	
	
	public String busquedaByFiltros(){

		//Listado		
		System.out.println(cicloLectivo);
		pslist = dao.findByCicloLectivo(cicloLectivo);
		pslist.addAll(dao.findByCuatrimestre(cuatrimestre));

		Set<PS> sinrepe = new HashSet<PS>(0);
		sinrepe.addAll(pslist);
		pslist.clear();
		pslist.addAll(sinrepe);
		
		if (pslist.size()==0){
			return "estPTGralesNohay";
		} else {
			return "estPTGralesListado";
		}
		
		//Totales
		

	}

	public List<PS> getPslist() {
		return pslist;
	}

	public void setPslist(List<PS> pslist) {
		this.pslist = pslist;
	}
	
	public int getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public int getCuatrimestre() {
		return cuatrimestre;
	}

	public void setCuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public List<LineaDeReporte> getLinealist() {
		return linealist;
	}

	public void setLinealist(List<LineaDeReporte> linealist) {
		this.linealist = linealist;
	}

	public LineaDeReporte getLinea() {
		return linea;
	}

	public void setLinea(LineaDeReporte linea) {
		this.linea = linea;
	}
	
}
