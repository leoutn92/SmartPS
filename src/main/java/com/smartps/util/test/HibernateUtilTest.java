package com.smartps.util.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.primefaces.validate.bean.AssertTrueClientValidationConstraint;

import com.smartps.model.Alumno;
import com.smartps.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void testGetSessionFactory() {
		assertEquals(HibernateUtil.getSessionFactory()!=null,true);
	}
	@Test
	public void testOpenSession() {
		assertEquals(HibernateUtil.getSessionFactory().openSession()!=null,true);
	}
	
	@Test
	public void testAMano(){
		assertTrue(HibernateUtil.getSessionFactory().openSession().get(Alumno.class, 18189).getNombre().equals("Vallejos Lucas Matias"));
	}
}
