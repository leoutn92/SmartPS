package com.smartps.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.OrganizacionDAO;
import com.smartps.model.Organizacion;

public class OrganizacionDaoTest {
	Organizacion  org;
	
	@Before
	public void setUp() throws Exception {
		org = new Organizacion(); //Se ejecuta antes de cada test.crea la entidad
		org.setNombre("Otra organizacion");
		org.setDescripsion("No hacen nada");
	}

	@After
	public void tearDown() throws Exception {
		 //se ejecuta despues. 
	}

	@Test
	public void testGetbydi() {
		org = OrganizacionDAO.getInstance().getById(1);
		assertFalse(org.getNombre(),org.equals(null));		
	}
	
	@Test
	public void testGetAll(){
		assertTrue(OrganizacionDAO.getInstance().getAll().size()!=0);
	}
	
	@Test
	public void testSet(){
		int cantant = OrganizacionDAO.getInstance().getAll().size();
		OrganizacionDAO.getInstance().save(org);
		int cantpos = OrganizacionDAO.getInstance().getAll().size();
		OrganizacionDAO.getInstance().delete(org);
		assertTrue("no inserto ni mierda",cantant!=cantpos);		
	}
	
	@Test 
	public void testDelete(){
		int cantant = OrganizacionDAO.getInstance().getAll().size();
		OrganizacionDAO.getInstance().save(org);		
		OrganizacionDAO.getInstance().delete(org);
		int cantpos = OrganizacionDAO.getInstance().getAll().size();
		assertTrue("no borro ni mierda",cantant==cantpos);
	}
	
	
	
	

}
