package com.smartps.beans;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.smartps.dao.PSDAO;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.LineaDeReporte;

@ManagedBean
public class GenerarReportePG {
//GenerarReportePG = GenerarReportePlanesGeneral_hdu6 
	
	private List<PS> pslist;
	private List<LineaDeReporte> linealist;
	
	
	private int cicloLectivo;
	private int cuatrimestre;
	private Date desde;
	private Date hasta;
	
	private LineaDeReporte linea;
	
	int id;
	PS ps;
	
	@PostConstruct
	public void init(){
		ps = new PS();
	}	
	
	public void buscarPS(){
		ps= new PSDAO().findById(id);
	}
	
	public void busquedaByFiltros(){
		pslist = new PSDAO().findByCicloLectivo(cicloLectivo);

/*		int cont = 0;
		for (int i=0; i<pslist.size(); i++){

			List<PlanDeTrabajo> planes = new ArrayList<PlanDeTrabajo>(pslist.get(i)
					.getPlanDeTrabajo().iterator().next().getFechaDePresentacion());
			int tamaño = pslist.get(i).getPlanDeTrabajo().size();
System.out.println("corre? "+tamaño);
			for (int j=0; j<tamaño; j++){
				linea.setFechaDePresentacion(pslist.get(i)
						.getPlanDeTrabajo().iterator().next().getFechaDePresentacion());
				linea.setFechaDePresentacion(planes.get(j).getFechaDePresentacion());
				linea.setTitulo(pslist.get(i).getTitulo());
				linea.setEstado(pslist.get(i).getEstado().getNombre());
				linea.setArea(pslist.get(i).getArea().getNombre());
				linea.setTipoActividad(pslist.get(i).getTipoActividad().getNombre());
				linea.setAlumno(pslist.get(i).getAlumno().getNombre());
				linea.setIngreso(pslist.get(i).getAlumno().getCicloLectivo());
				linealist.add(cont, linea);
				cont++;
				
			}
			
		}
*/
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

	public PS getPs() {
		return ps;
	}

	public void setPs(PS ps) {
		this.ps = ps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
