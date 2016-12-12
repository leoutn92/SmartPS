package com.smartps.beans.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.beans.vencimientos.LineaTablaVencimiento;
import com.smartps.beans.vencimientos.VencimientosPlanesBean;

public class VencimientoPlanBeanTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		VencimientosPlanesBean bean =new VencimientosPlanesBean();
		bean.init();
		
		for(LineaTablaVencimiento l : bean.getPlanes()){
			System.out.println(l.getFechaAprobDesaprob().getYear());
		}
		
	}

}
