package com.smartps.util.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.PSDao;

public class TestVarios {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(Calendar.getInstance().get(Calendar.YEAR)==2016);
	}
	
	@Test
	public void testPSvigente(){
		assertTrue(new PSDao().getById(22).getEstado().getNombre(),new PSDao().getById(22).estaVigente());
	}
	
}
