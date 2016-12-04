package com.smartps.beans.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.beans.NotificacionesEmailBean;

public class NotifEmailBeanTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArmarCorreo() {
		NotificacionesEmailBean bean = new NotificacionesEmailBean();
		bean.init();
		bean.armarCorreo();
		System.out.println(bean.getMensaje());
		assertTrue(true);
	}
	
	@Test
	public void testEnviarCorreso(){
		NotificacionesEmailBean bean = new NotificacionesEmailBean();
		bean.init();
		bean.enviarCorreos();
		assertTrue(true);
	}
	


}
