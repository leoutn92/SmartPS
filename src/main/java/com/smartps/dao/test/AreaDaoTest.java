package com.smartps.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.AreaDao;

public class AreaDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuscarArea() {
		assertTrue(AreaDao.getInstance().getById(2).getNombre().equals("Redes"));
	}

}
