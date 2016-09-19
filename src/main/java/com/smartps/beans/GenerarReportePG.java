package com.smartps.beans;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.smartps.dao.PSDAO;
import com.smartps.dao.PlanDeTrabajoDAO;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.LineaDeReporte;

@ManagedBean
public class GenerarReportePG {
//GenerarReportePG = GenerarReportePlanesGeneral_hdu6 
	
	private PSDAO psdao = new PSDAO();
	private PlanDeTrabajoDAO ptdao = new PlanDeTrabajoDAO();

	private List<PS> pslist;
	private List<PS> pslistCL;
	private List<PS> pslistCuatri;
	private List<PlanDeTrabajo> planes;
	private List<PlanDeTrabajo> planesPeriodo;	
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
	
	
	@PostConstruct
	public void init(){
		pslist = new ArrayList<PS>();
		pslistCL = new ArrayList<PS>();
		pslistCuatri = new ArrayList<PS>();
		planes = new ArrayList<PlanDeTrabajo>();
		planesPeriodo = new ArrayList<PlanDeTrabajo>();
		linealist = new ArrayList<LineaDeReporte>();
	}
	
	public String busquedaByFiltros(){

	//Listado		

		planes = ptdao.retrieveAll();
		int contId = 1;
		
		//Por CicloLectivo
		pslistCL = psdao.findByCicloLectivo(cicloLectivo);
		for (int i=0; i<pslistCL.size(); i++){			
			for (int j=0; j<planes.size(); j++){
				if (pslistCL.get(i).getId()==planes.get(j).getPs().getId()){
					linea = new LineaDeReporte();
					linea.setId(contId);
					linea.setFechaDePresentacion(planes.get(j).getFechaDePresentacion());
					linea.setTitulo(pslistCL.get(i).getTitulo());
					linea.setEstado(pslistCL.get(i).getEstado().getNombre());
					linea.setArea(pslistCL.get(i).getArea().getNombre());
					linea.setTipoActividad(pslistCL.get(i).getTipoActividad().getNombre());
					linea.setAlumno(pslistCL.get(i).getAlumno().getNombre());
					linea.setIngreso(pslistCL.get(i).getAlumno().getCicloLectivo());
					linealist.add(linea);
					contId++;
				}
			}
		}
System.out.println("fin ciclolectivo");			
		//Por Cuatrimestre
		pslistCuatri = psdao.findByCuatrimestre(cuatrimestre);
		for (int x=0; x<pslistCuatri.size(); x++){			
			for (int y=0; y<planes.size(); y++){
				if (pslistCuatri.get(x).getId()==planes.get(y).getPs().getId()){
					linea = new LineaDeReporte();
					linea.setId(contId);
					linea.setFechaDePresentacion(planes.get(y).getFechaDePresentacion());
					linea.setTitulo(pslistCuatri.get(x).getTitulo());
					linea.setEstado(pslistCuatri.get(x).getEstado().getNombre());
					linea.setArea(pslistCuatri.get(x).getArea().getNombre());
					linea.setTipoActividad(pslistCuatri.get(x).getTipoActividad().getNombre());
					linea.setAlumno(pslistCuatri.get(x).getAlumno().getNombre());
					linea.setIngreso(pslistCuatri.get(x).getAlumno().getCicloLectivo());
					linealist.add(linea);
					contId++;
				}
			}
		}
		System.out.println("fin cuatrimestre: "+desde);		
		//Por Periodo
		if ((desde!=null) && (hasta!=null)){
			pslist = psdao.retrieveAll();
			planesPeriodo = ptdao.findByPeriodo(desde, hasta);
			for (int a=0; a<planesPeriodo.size(); a++){
				for (int b=0; b<pslist.size(); b++){
					if (planesPeriodo.get(a).getPs().getId()==pslist.get(b).getId()){
						linea = new LineaDeReporte();
						linea.setId(contId);
						linea.setFechaDePresentacion(planesPeriodo.get(a).getFechaDePresentacion());
						linea.setTitulo(pslist.get(b).getTitulo());
						linea.setEstado(pslist.get(b).getEstado().getNombre());
						linea.setArea(pslist.get(b).getArea().getNombre());
						linea.setTipoActividad(pslist.get(b).getTipoActividad().getNombre());
						linea.setAlumno(pslist.get(b).getAlumno().getNombre());
						linea.setIngreso(pslist.get(b).getAlumno().getCicloLectivo());
						linealist.add(linea);
						contId++;
					}
				}
			}
			System.out.println("fin periodo");
		}
		//Eliminar Repetidos
		Set<LineaDeReporte> sinrepe = new HashSet<LineaDeReporte>(0);
		sinrepe.addAll(linealist);
		linealist.clear();
		linealist.addAll(sinrepe);		

		System.out.println("fin repetidos");
		
		if (linealist.size()==0){
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

	public List<PS> getPslistCL() {
		return pslistCL;
	}

	public void setPslistCL(List<PS> pslistCL) {
		this.pslistCL = pslistCL;
	}

	public List<PS> getPslistCuatri() {
		return pslistCuatri;
	}

	public void setPslistCuatri(List<PS> pslistCuatri) {
		this.pslistCuatri = pslistCuatri;
	}

	public List<PlanDeTrabajo> getPlanes() {
		return planes;
	}

	public void setPlanes(List<PlanDeTrabajo> planes) {
		this.planes = planes;
	}

	public List<PlanDeTrabajo> getPlanesPeriodo() {
		return planesPeriodo;
	}

	public void setPlanesPeriodo(List<PlanDeTrabajo> planesPeriodo) {
		this.planesPeriodo = planesPeriodo;
	}

	public List<LineaDeReporte> getLinealist() {
		return linealist;
	}

	public void setLinealist(List<LineaDeReporte> linealist) {
		this.linealist = linealist;
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
	
	public LineaDeReporte getLinea() {
		return linea;
	}

	public void setLinea(LineaDeReporte linea) {
		this.linea = linea;
	}

}
