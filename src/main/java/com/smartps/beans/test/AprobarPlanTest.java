package com.smartps.beans.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.smartps.beans.aprobarPlan.AprobarPlan;
import com.smartps.beans.aprobarPlan.LineaTablaPlanesPresentados;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.PSDao;

public class AprobarPlanTest {
	AprobarPlan aprobarPlan = AprobarPlan.getInstance();
	PSDao psDao = PSDao.getInstance();
	EstadoDao estadoDao = EstadoDao.getInstance(); 
	@Test
	public void buscarPlanesPresentados() {
		aprobarPlan.updateTablaPlanesPresentados();
		assertTrue(!aprobarPlan.getTablaPlanesPresentados().isEmpty());
	}
//	
//	@Test
//	public void aprobarPlan() {
//		LineaTablaPlanesPresentados linea = aprobarPlan.getTablaPlanesPresentados().get(0);
//		aprobarPlan.aprobar(linea);
//		aprobarPlan.updateTablaPlanesPresentados();
//		assertTrue(aprobarPlan.getTablaPlanesPresentados().isEmpty());
//	}
}
