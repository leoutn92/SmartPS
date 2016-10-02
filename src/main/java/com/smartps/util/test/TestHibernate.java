package com.smartps.util.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.dao.AlumnoDAO;
import com.smartps.model.Alumno;
import com.smartps.util.HibernateUtil;

public class TestHibernate {
	AlumnoDAO dao = new AlumnoDAO();
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
			HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

	@Test
	public void testgetbydi(){
		assertTrue(dao.getById(18189).getNombre().equals("Vallejos Lucas Matias"));
	}
	
	@Test
	public void testGetall(){
		assertTrue(dao.getAll().size()==2);
	}
	
	@Test
	public void testGetcurrentSession(){
		assertTrue(HibernateUtil.getSessionFactory().getCurrentSession().isOpen());
	}
	
	@Test
	public void test2Get(){
		Alumno alu = dao.getById(18189);
		assertFalse(alu== dao.getById(17848));
	}
	

	

}
