package com.smartps.beans.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import com.smartps.beans.desicionDelConsejoInforme.DesicionDelConsejoInformes;
import com.smartps.beans.desicionDelConsejoInforme.LineaInformeParaDecision;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;

public class DecisionDelConsejoInformeTest {
	DesicionDelConsejoInformes desCon= new DesicionDelConsejoInformes();
	int idInforme = 1;
	int idPS = 1;
	InformeFinalDao iDao= new InformeFinalDao();
	PSDao pDao = new PSDao();
	EstadoDao eDao = EstadoDao.getInstance();
	String observaciones="observacion";
	static Integer ordenanza=1;
	static Date fechaEvaluacion=new Date();
	@Test
	public void shouldUpdateTablaInformesPresentados() {
		desCon.updateTablaInformesParaDecision();
		assertTrue(desCon.getTablaInformesParaDecision().isEmpty());		
	}
	@Test
	public void shouldEvaluarInforme() {
		desCon.updateTablaInformesParaDecision();
		LineaInformeParaDecision linea = desCon.getLinea(idInforme);
		linea.setObservaciones(observaciones);
		linea.setFechaEvaluacion(fechaEvaluacion);
		linea.setOrdenanza(ordenanza);
		desCon.evaluar(idInforme);
		InformeFinal informe = iDao.getById(1);
		assertEquals(informe.getObservaciones(),observaciones);
		assertEquals(informe.getFechaAprobDesaprob().getMinutes(),fechaEvaluacion.getMinutes());
		assertEquals(informe.getOrdenanza(),ordenanza);
	}
	@Test
	public void shouldAprobarInforme() {
		desCon.updateTablaInformesParaDecision();
		desCon.aprobar(idInforme);
		InformeFinal informe = iDao.findById(idInforme);
		PS ps = pDao.findById(informe.getId());	
		assertEquals(ps.getEstado().getId(),eDao.getEstadoInformeAprobado().getId());
	}
	
	@Test
	public void shouldDesaprobarInforme() {
		desCon.updateTablaInformesParaDecision();
		desCon.desaprobar(idInforme);
		InformeFinal informe = iDao.findById(idInforme);
		PS ps = pDao.findById(informe.getId());	
		assertEquals(ps.getEstado().getId(),eDao.getEstadoInformeObservado().getId());
	}
	
	@After
	public void after() {
		pDao.updateEstado(idPS,eDao.getEstadoInformePresentado().getId());
		InformeFinal informe = iDao.findById(idInforme);
		informe.setFechaAprobDesaprob(null);
		informe.setObservaciones(null);
		informe.setOrdenanza(0);
		iDao.update(informe);
	}
	
}
