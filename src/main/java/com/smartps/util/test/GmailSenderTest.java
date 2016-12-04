package com.smartps.util.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smartps.beans.NotificacionesEmailBean;
import com.smartps.util.GmailSender;

public class GmailSenderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testenviaDeAUno() {
		new GmailSender().sentMessage("lucasv666@gmail.com", "hola que tal") ;
		assertTrue(true);
	}

//	@Test
//	public void testEnviaTodoJunto(){
//		new GmailSender().sentAllMessages("<table><tr><th>Company</th><th>Contact</th><th>Country</th></tr>  <tr>    <td>Alfreds Futterkiste</td>    <td>Maria Anders</td>    <td>Germany</td>  </tr>  <tr>    <td>Centro comercial Moctezuma</td>    <td>Francisco Chang</td>    <td>Mexico</td>  </tr>  <tr>    <td>Ernst Handel</td>    <td>Roland Mendel</td>    <td>Austria</td>  </tr>  <tr>    <td>Island Trading</td>    <td>Helen Bennett</td>    <td>UK</td>  </tr>  <tr>    <td>Laughing Bacchus Winecellars</td>    <td>Yoshi Tannamuri</td>    <td>Canada</td>  </tr>  <tr>    <td>Magazzini Alimentari Riuniti</td>    <td>Giovanni Rovelli</td>    <td>Italy</td>  </tr></table>");
//	}
}
