package com.smartps.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.validate.bean.AssertTrueClientValidationConstraint;

import com.smartps.dao.EstadoDao;

public class EstadoDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(EstadoDao.getInstance().getNameById(1).equals(EstadoDao.getInstance().getById(1).getNombre()));
	}
	
	@Test
	public void test2(){
		assertTrue(EstadoDao.getInstance().buscarPorNombre("PS Aprobada").getNombre().equals("PS aprobada"));
	}

}
