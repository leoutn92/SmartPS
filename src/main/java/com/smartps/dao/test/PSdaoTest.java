package com.smartps.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

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
	PSDao dao = new  PSDao();
	
	@Before
	public void setUp() throws Exception {
		ps.setAlumno(new AlumnoDAO().getById(18189));
		
		ps.setArea(AreaDao.getInstance().getById(1));
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
	
	@Test
	public void testgetplanes(){
		PS sp = new AlumnoDAO().getMostRecentPS(18189);
		List<PlanDeTrabajo> lista = dao.getPlanes(sp.getId());
		assertTrue(sp.getTitulo(),lista.size()>0);
	}
	
	
	

}
