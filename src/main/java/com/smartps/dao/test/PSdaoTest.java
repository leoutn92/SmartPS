package com.smartps.dao.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.OrganizacionDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

public class PSdaoTest {
	PS ps=new PS();
	
	@Before
	public void setUp() throws Exception {
		ps.setAlumno(AlumnoDAO.getInstance().buscarAlumno(18189));
		
		ps.setArea(AreaDao.getInstance().buscarArea(1));
		ps.setCicloLectivo(2016);
		ps.setCuatrimestre(2);
		ps.setEstado(EstadoDao.getInstance().buscarPorNombre("Plan Presentado"));
		ps.setOrganizacion(OrganizacionDao.getInstance().getAll().get(0));
		ps.setTipoActividad(TipoActividadDao.getInstance().getAll().get(0));
		ps.setTitulo("Ps de Prueba");		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEstadoDao() {
		assertFalse(EstadoDao.getInstance().buscarPorNombre("Plan Presentado")==null);
	}
	
	@Test
	public void testSave(){
		int valant = PSDao.getInstance().getAll().size();
		PSDao.getInstance().save(ps);	
		int valpos = PSDao.getInstance().getAll().size();
		assertTrue(valant==valpos-1);
	}
	
	//este test falla. arreglar PSDao
	@Test
	public void testDelete(){
		int valant = PSDao.getInstance().getAll().size();
		PSDao.getInstance().delete(ps);
		int valpos = PSDao.getInstance().getAll().size();
		assertTrue(valant==valpos+1);
	}
	
//	@Test
//	public void testUpdate(){
//		PlanDeTrabajo plan = new PlanDeTrabajo();
//		plan.setPs(ps);
//		plan.setFechaDePresentacion(new Date());
//		plan.setObservaciones("presentacion de prueba");
//		ps.getPlanDeTrabajo().add(plan);
//		new PSDao().save(ps);
//		new PSDao().update(ps);
//		assertTrue(true); //no se que preguntar xD
//		
//	}

}
