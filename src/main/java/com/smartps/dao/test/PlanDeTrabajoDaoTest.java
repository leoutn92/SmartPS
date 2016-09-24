package com.smartps.dao.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.OrganizacionDAO;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

public class PlanDeTrabajoDaoTest {
	PlanDeTrabajo plan=new PlanDeTrabajo();
	PS ps = new PS();
	
	@Before
	public void setUp() throws Exception {
		
		ps.setAlumno(new AlumnoDAO().buscarAlumno(18189));		
		ps.setArea(AreaDao.getInstance().buscarArea(1));
		ps.setCicloLectivo(2016);
		ps.setCuatrimestre(2);
		ps.setEstado(new EstadoDao().buscarPorNombre("Plan Presentado"));
		ps.setOrganizacion(OrganizacionDAO.getInstance().getAll().get(0));
		ps.setTipoActividad(TipoActividadDao.getInstance().getAll().get(0));
		ps.setTitulo("Ps de Prueba");
		new PSDao().save(ps);
		
		plan.setPs(ps);
		plan.setFechaDePresentacion(new Date());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		PlanDeTrabajoDao.getInstance().save(plan);
		assertTrue(true);//ponele
		
	}

}
