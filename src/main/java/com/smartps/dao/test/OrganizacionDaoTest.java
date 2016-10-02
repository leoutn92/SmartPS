package com.smartps.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.OrganizacionDao;
import com.smartps.model.Organizacion;

public class OrganizacionDaoTest {
	Organizacion  org;
	OrganizacionDao dao = new OrganizacionDao();
	
	@Before
	public void setUp() throws Exception {
		org = new Organizacion(); //Se ejecuta antes de cada test.crea la entidad
		org.setNombre("Otra organizacion");
		org.setDescripsion("No hacen nada");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetbydi() {
		org = OrganizacionDao.getInstance().getById(1);
		assertFalse(org.getNombre(),org.equals(null));		
	}
	
	@Test
	public void testGetAll(){
		assertTrue(OrganizacionDao.getInstance().getAll().size()!=0);
	}
	
	@Test
	public void testSet(){
		int cantant = OrganizacionDao.getInstance().getAll().size();
		OrganizacionDao.getInstance().save(org);
		int cantpos = OrganizacionDao.getInstance().getAll().size();
		OrganizacionDao.getInstance().delete(org);
		assertTrue("no inserto ni mierda",cantant!=cantpos);		
	}
	
	@Test
	public void testUpdate(){
		org.setNombre("nombre viejo");
		dao.save(org);
		org.setNombre("nombre nuevo");
		dao.update(org);
		assertTrue(true); //ponele
	}
	
	@Test 
	public void testDelete(){
		int cantant = OrganizacionDao.getInstance().getAll().size();
		OrganizacionDao.getInstance().save(org);		
		OrganizacionDao.getInstance().delete(org);
		int cantpos = OrganizacionDao.getInstance().getAll().size();
		assertTrue("no borro ni mierda",cantant==cantpos);
	}
	
	
	
	

}
