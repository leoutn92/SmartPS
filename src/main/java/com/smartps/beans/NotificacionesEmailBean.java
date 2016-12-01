package com.smartps.beans;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.dao.ReceptorEmailDao;
import com.smartps.model.DocumentoDePS;
import com.smartps.model.InformeFinal;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.GmailSender;

@ManagedBean
@ViewScoped
public class NotificacionesEmailBean {
	
	PlanDeTrabajoDao daopalnes = new PlanDeTrabajoDao();
	InformeFinalDao daoinformes = new InformeFinalDao();
	
	List<InformeFinal> informes;
	List<PlanDeTrabajo> planes;
	
	String mensaje;	
	
	@PostConstruct
	public void init(){
		informes=daoinformes.getNoInformados();
		planes=daopalnes.getNoInformados();
		
	}

	public void enviarCorreos(){
		this.armarCorreo();
		System.out.println(mensaje);
		System.out.println(new ReceptorEmailDao().getAll().size());
		new GmailSender().sentAllMessages(mensaje);
	}
	
	public void armarCorreo(){
		mensaje="<style>table,th,td{border:1px solid black;}</style>";
		mensaje=mensaje+"<p>Le informamos respecto a las ultimas novedades sobre la materia Practica Supervisada (FRRe-UTN)</p>";
		mensaje=mensaje+"<p>Evaluaciones de Planes de Práctica supervisada Presentados </p>";
		mensaje=mensaje +"<table><thead><tr><th>Nombre del alumno</th><th>Titulo de la practica supervisada</th><th>Fecha de Presentacion</th><th>Fecha de Evaluacion</th><th>Observaciones</th></tr></thead>";
		for(DocumentoDePS plan : planes){
			mensaje=mensaje+this.armarColumna(plan);
		}
		mensaje=mensaje+"</table><br></br>";
		
		mensaje=mensaje+"<p>Evaluaciones de Informes Finales supervisada Presentados </p>";
		mensaje=mensaje +"<table><thead><tr><th>Nombre del alumno</th><th>Titulo de la practica supervisada</th><th>Fecha de Presentacion</th><th>Fecha de Evaluacion</th><th>Observaciones</th></tr></thead>";
		for(DocumentoDePS inf : informes){
			mensaje=mensaje+this.armarColumna(inf);
		}
		mensaje=mensaje+"</table><br></br>";
	}
	
	private String armarColumna(DocumentoDePS doc){
		String col = "<tr><td>"+doc.getPs().getAlumno().getNombre()+"</td>";
		col=col+"<td>"+doc.getPs().getTitulo()+"</td>";
		col =col+ "<td>"+ new SimpleDateFormat("dd-MM-yyyy").format(doc.getFechaDePresentacion())+"</td><td>";
		if (doc.getFechaAprobDesaprob()!=null){
			col=col+ (new  SimpleDateFormat("dd-MM-yyyy").format( doc.getFechaAprobDesaprob())).toString();
		};
		col=col+"</td><td>";
		if (doc.getObservaciones()!=null){
			col=col + doc.getObservaciones();
		}
		col=col+"</td></tr>";
		return col;
	}
	
	public List<InformeFinal> getInformes() {
		return informes;
	}


	public void setInformes(List<InformeFinal> informes) {
		this.informes = informes;
	}


	public List<PlanDeTrabajo> getPlanes() {
		return planes;
	}


	public void setPlanes(List<PlanDeTrabajo> planes) {
		this.planes = planes;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
