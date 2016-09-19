package com.smartps.beans.test;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import com.smartps.beans.RegistrarPresentacionInformeBean;
import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.OrganizacionDAO;
import com.smartps.dao.PSDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.Alumno;
import com.smartps.model.Area;
import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.Organizacion;
import com.smartps.model.PS;
import com.smartps.model.TipoActividad;
public class RegistrarPresentacionInformeBeanTest {
	public static int legajo=18671;
	public static Alumno alumno = new Alumno();
	public static PS ps = new PS();
	public static AlumnoDAO alumnoDao = new AlumnoDAO();
	public static InformeFinal informe = new InformeFinal();
	public static PSDao psdao = new PSDao();
	public static EstadoDao estadoDao = new EstadoDao();
	public static Organizacion org= new Organizacion();
	public static OrganizacionDAO orgDao=new OrganizacionDAO();
	public static TipoActividad tActividad = new TipoActividad();
	public static TipoActividadDao tipoActDao = new TipoActividadDao();
	public static Area area =new Area();
	public static AreaDao areaDao = new AreaDao();
	public static InformeFinalDao informeFinalDao = new InformeFinalDao();
	public static Estado estadoPlanAprobado = new Estado();
	public static Estado estadoInformePresentado = new Estado();
	public static RegistrarPresentacionInformeBean registrarPresentacionInformeBean=new RegistrarPresentacionInformeBean();
	public static Date fechaPresentacion = new Date();
	@BeforeClass
	public static void beforeTest() {
		alumno.setLegajo(legajo);
		alumnoDao.save(alumno);
		estadoPlanAprobado.setNombre("Plan aprobado");
		estadoPlanAprobado.setDescripsion("desc");
		estadoDao.save(estadoPlanAprobado);
		estadoInformePresentado.setNombre("Informe presentado");
		estadoInformePresentado.setDescripsion("desc");
		estadoDao.save(estadoInformePresentado);
		ps.setAlumno(alumno);
		area.setNombre("area");
		area.setDescripsion("desc");
		areaDao.save(area);
		org.setNombre("orga");
		org.setDescripsion("desc");
		orgDao.save(org);
		tActividad.setNombre("orga");
		tActividad.setDescripsion("desc");
		tipoActDao.save(tActividad);
		ps.setTipoActividad(tActividad);
		ps.setOrganizacion(org);
		ps.setArea(area);
		ps.setEstado(estadoPlanAprobado);
		ps.setTitulo("Titulo");
		psdao.save(ps);
		informe.setPs(ps);
		informe.setFechaDePresentacion(fechaPresentacion);
		informeFinalDao.save(informe);
	}
	@Test
	public void getIdEstadoPlanAprobado() {
		int estado = registrarPresentacionInformeBean.getIdEstadoPlanAprobado();
		assertEquals(estado==0,false);
	}
	@Test
	public void succesSearchPsConPlanAprobado() {
		PS ps = registrarPresentacionInformeBean.searchPsConPlanAprobado(legajo);
		assertEquals(ps==null,false);
		assertEquals(ps.getAlumno().getLegajo()==legajo,true);
	}
	@Test
	public void successRegistrarPresentacionInforme() {
		registrarPresentacionInformeBean.setPs(ps);
		registrarPresentacionInformeBean.setFechaPresentacion(fechaPresentacion);
		registrarPresentacionInformeBean.registrarPresentacionInforme();
		int idEstadoInformePresentado = registrarPresentacionInformeBean.getIdEstadoInformePresentado() ;
		int idps=psdao.searchPs(legajo, idEstadoInformePresentado).getId();
		assertEquals(informeFinalDao.getByIdPs(idps).isEmpty(),false);
		assertEquals(psdao.searchPs(legajo, idEstadoInformePresentado)!=null,true);
 	}	
}
