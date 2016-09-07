package com.smartps.beans.test;
import org.junit.Before;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.PSDao;
import com.smartps.model.Alumno;
import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
public class BuscarPS {
	public Alumno alumno = new Alumno();
	public PS ps = new PS();
	public AlumnoDAO alumnoDao = new AlumnoDAO();
	public InformeFinal informe = new InformeFinal();
	public PSDao psdao = new PSDao();
	public Estado estado = new Estado();
	
	@Before
	public void beforeTest() {
		alumno.setLegajo(18671);
		alumnoDao.save(alumno);
		estado.setNombre("plan aprobado");
		psdao.save(ps);
		psdao.registrarInforme(ps);
	}
	
}
