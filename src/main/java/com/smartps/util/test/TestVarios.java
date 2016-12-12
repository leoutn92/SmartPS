package com.smartps.util.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testFechas(){
		Date date = new Date();
		LocalDate fecha1 =LocalDate.now();
		LocalDate fecha2 = LocalDate.of(date.getYear()-100+2000, date.getMonth()+1, date.getDate());
		Date date2= Date.from(fecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println(fecha1);
		System.out.println(fecha2);
		System.out.println(date);
		System.out.println(date2);
		System.out.println(date.equals(date2));
		System.out.println(fecha1.equals(fecha2));
		System.out.println(ChronoUnit.DAYS.between(fecha1, fecha2));	
		assertTrue(true);
	}
	

	
}
