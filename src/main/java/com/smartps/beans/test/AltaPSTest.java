package com.smartps.beans.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.beans.AltaPS;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.PSDao;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

public class AltaPSTest {
	AltaPS bean= new AltaPS();
	
	
	@Before	
	public void setUp() throws Exception {
		bean.init();
		bean.getPs().getAlumno().setLegajo(18189);
		bean.buscarAlumno();
		bean.setActSelec("1");
		bean.setAreaSelec("1");
		bean.setOrgSelec("1");
		
		bean.cambioAct();
		bean.cambioArea();
		bean.cambioOrg();
		
		bean.getPs().setCicloLectivo(2016);
		bean.getPs().setCuatrimestre(2);
		bean.getPs().setTitulo("PS de prueba - JUnit");
		bean.getPs().setEstado(EstadoDao.getInstance().buscarPorNombre("Plan Presentado"));
		
		bean.setPlan(new PlanDeTrabajo());
		bean.getPlan().setFechaDePresentacion(new Date());
		bean.getPlan().setPs(bean.getPs());
		bean.getPlan().setObservaciones("plan de prueba JUnit");
		
		
		
	}

	@After
	public void tearDown() throws Exception {
//		new PSDao().delete(bean.getPs());
	}

	@Test
	public void testBuscarAlumno() {
		assertTrue(bean.getPs().getAlumno().getNombre().equals("Vallejos Lucas Matias"));
	}
	
	@Test
	public void testListenerArea(){
		assertFalse(bean.getPs().getArea().getNombre()=="Desarrollo y programación");
		
	}

	@Test
	public void testListenerOrg(){
		assertFalse(bean.getPs().getOrganizacion().getNombre()=="UTN Facultad Regional Resistencia");
		
	}
	
	@Test
	public void testListenerTipoActividad(){
		assertFalse(bean.getPs().getTipoActividad().getNombre()=="Relacion Laboral");
		
	}
	
	@Test
	public void testGrabarPS(){
		int valant=PSDao.getInstance().getAll().size();
		bean.guardarPS();
		int valpos= PSDao.getInstance().getAll().size();
		assertTrue(valant==valpos-1);		
		
	}

}
