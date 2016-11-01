package com.smartps.dao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.validate.bean.AssertTrueClientValidationConstraint;

import com.smartps.dao.AlumnoDAO;
import com.smartps.model.Alumno;
import com.smartps.model.PS;

public class AlumnoDaoTest {
	AlumnoDAO dao=new AlumnoDAO();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		Alumno perez =  new AlumnoDAO().getById(9669);
		if (perez != null){
			dao.delete(perez);
		}
	}

	@Test
	public void testTienePSVigente() {
		assertFalse(dao.tienePSVigente(18189));//sabiendo que no hay ninguna ps vigente en la base
	}
	
	@Test
	public void testPuedePresentarPlan(){
		assertTrue(dao.puedePresentarPlan(18189));//sabiendo que puede presentar plan
	}
	
	@Test
	public void testUltimaPS(){
		PS ps = dao.getMostRecentPS(18189);
		assertTrue(ps.getId()==117);//sabiendo que la ps 117 esta vigente y es la unica vigente		
		
	}
	
	@Test
	public void testGetByID(){
		assertTrue(dao.getById(18189).getNombre().equals("Vallejos Lucas Matias"));
	}
	
//	@Test
//	public void testSave(){
//		Alumno alu = new Alumno();
//		alu.setNombre("Juan Perez");
//		alu.setLegajo(9669);
//		alu.setCicloLectivo(1);
//		new AlumnoDAO().save(alu);
//		assertTrue(dao.getById(9669).getNombre().equals("Juan Perez"));
//	}
	
	@Test
	public void testaproboPS(){
		assertTrue(dao.aproboPS(18189));
	}

}
