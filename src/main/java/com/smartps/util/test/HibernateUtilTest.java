package com.smartps.util.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smartps.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void testGetSessionFactory() {
		assertEquals(HibernateUtil.getSessionFactory()!=null,true);
	}
}
