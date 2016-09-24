package com.smartps.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.AlumnoDAO;

public class AlumnoDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTienePSVigente() {
		assertFalse(AlumnoDAO.getInstance().tienePSVigente(18189));//sabiendo que no hay ninguna ps vigente en la base
	}
	
	@Test
	public void testPuedePresentarPlan(){
		assertTrue(AlumnoDAO.getInstance().puedePresentarPlan(18189));//sabiendo que puede presentar plan
	}

}
