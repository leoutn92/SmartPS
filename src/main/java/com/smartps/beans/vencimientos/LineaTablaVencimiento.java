package com.smartps.beans.vencimientos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.smartps.model.DocumentoDePS;
import com.smartps.model.PlanDeTrabajo;

public class LineaTablaVencimiento  extends DocumentoDePS{	
	private LocalDate fechaVencimiento,fechaEvaluacion;
		
	
	public LineaTablaVencimiento(DocumentoDePS p){		
		this.setFechaAprobDesaprob(p.getFechaAprobDesaprob());
		this.setDirDocumentoDigital(p.getDirDocumentoDigital());
		this.setFechaDePresentacion(p.getFechaDePresentacion());
		this.setFile(p.getFile());
		this.setId(p.getId());
		this.setNotificadoEmail(p.isNotificadoEmail());
		this.setObservaciones(p.getObservaciones());
		this.setOrdenanza(p.getOrdenanza());
		this.setPs(p.getPs());		
		this.fechaEvaluacion=LocalDate.of(this.getFechaAprobDesaprob().getYear()-100+2000, this.getFechaAprobDesaprob().getMonth()+1, this.getFechaAprobDesaprob().getDate());
		this.fechaVencimiento=fechaEvaluacion.plusYears(1);
	}
	
	
	public Date getFechaVencimiento(){
		return Date.from(fechaVencimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());		
	}
	
	public long getDiasRestantes(){
		return ChronoUnit.DAYS.between(LocalDate.now(),fechaVencimiento);
	};
	
	public boolean isVencido(){
		return this.getDiasRestantes()<0;
	}
}
