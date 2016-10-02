package com.smartps.beans.test;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smartps.beans.registrarPresentacionInforme.CriteriosParaFiltrarPs;
import com.smartps.beans.registrarPresentacionInforme.LineaTablaInformes;
import com.smartps.beans.registrarPresentacionInforme.RegistrarPresentacionInformeBean;
import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.AreaDao;
import com.smartps.dao.EstadoDao;
import com.smartps.dao.InformeFinalDao;
import com.smartps.dao.OrganizacionDao;
import com.smartps.dao.PSDao;
import com.smartps.dao.PlanDeTrabajoDao;
import com.smartps.dao.TipoActividadDao;
import com.smartps.model.Alumno;
import com.smartps.model.Area;
import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.Organizacion;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.TipoActividad;
public class RegistrarPresentacionInformeBeanTest {
	public static int legajo1=18671;
	public static int legajo2=18670;
	public static Alumno alumno1 = new Alumno();
	public static Alumno alumno2 = new Alumno();
	public static PS ps1 = new PS();
	public static PS ps2 = new PS();
	public static AlumnoDAO alumnoDao = new AlumnoDAO();
	public static InformeFinal informe1 = new InformeFinal();
	public static InformeFinal informe2 = new InformeFinal();
	public static PSDao psdao = PSDao.getInstance();
	public static EstadoDao estadoDao = EstadoDao.getInstance();
	public static Organizacion org= new Organizacion();
	public static OrganizacionDao orgDao=OrganizacionDao.getInstance();
	public static TipoActividad tActividad = new TipoActividad();
	public static TipoActividadDao tipoActDao = TipoActividadDao.getInstance();
	public static PlanDeTrabajo plan1 = new PlanDeTrabajo();
	public static PlanDeTrabajo plan2 = new PlanDeTrabajo();
	public static PlanDeTrabajoDao planDeTrabajoDao = PlanDeTrabajoDao.getInstance();
	public static Area area =new Area();
	public static AreaDao areaDao = AreaDao.getInstance();
	public static InformeFinalDao informeFinalDao = InformeFinalDao.getInstance();
	public static Estado estadoPlanAprobado = new Estado();
	public static Estado estadoInformePresentado = new Estado();
	public static Estado estadoInformeObservado = new Estado();
	public static RegistrarPresentacionInformeBean registrarPresentacionInformeBean=new RegistrarPresentacionInformeBean();
	public static Date fechaPresentacion = new Date();
	public static String psTitle = "Titulo";
	public static String nombreAlumno= "nombre Alumno";
	@BeforeClass
	public static void beforeTest() {
		estadoPlanAprobado.setNombre("Plan aprobado");
		estadoPlanAprobado.setDescripsion("desc");
		estadoDao.save(estadoPlanAprobado);
		estadoInformePresentado.setNombre("Informe presentado");
		estadoInformePresentado.setDescripsion("desc");
		estadoDao.save(estadoInformePresentado);
		estadoInformeObservado.setNombre("Informe observado");
		estadoInformeObservado.setDescripsion("desc");
		estadoDao.save(estadoInformeObservado);
		area.setNombre("area");
		area.setDescripsion("desc");
		areaDao.save(area);
		org.setNombre("orga");
		org.setDescripsion("desc");
		orgDao.save(org);
		tActividad.setNombre("orga");
		tActividad.setDescripsion("desc");
		tipoActDao.save(tActividad);
		alumno1.setLegajo(legajo1);
		alumno1.setNombre(nombreAlumno);
		alumno1.addPs(ps1);
		alumnoDao.save(alumno1);
		ps1.setAlumno(alumno1);
		ps1.setTipoActividad(tActividad);
		ps1.setOrganizacion(org);
		ps1.setArea(area);
		ps1.setEstado(estadoPlanAprobado);
		ps1.setTitulo(psTitle);
		ps1.addPlan(plan1);
		psdao.save(ps1);
		alumno2.setLegajo(legajo2);
		alumno2.setNombre(nombreAlumno);
		alumno2.addPs(ps2);
		alumnoDao.save(alumno2);
		ps2.setAlumno(alumno2);
		ps2.setTipoActividad(tActividad);
		ps2.setOrganizacion(org);
		ps2.setArea(area);
		ps2.setEstado(estadoInformeObservado);
		ps2.setTitulo(psTitle);
		psdao.save(ps2);
	}
	@Test
	public void getIdEstadoPlanAprobado() {
		int estado = registrarPresentacionInformeBean.getIdEstadoPlanAprobado();
		assertEquals(estado==0,false);
	}
	@Test
	public void succesSearchPsParaPresentarInformeByPsTitle() { 
		CriteriosParaFiltrarPs criterios = new CriteriosParaFiltrarPs();
		criterios.setPsTitle(psTitle);
		registrarPresentacionInformeBean.setCriterios(criterios);
		PlanDeTrabajo plan= new PlanDeTrabajo();
		PS ps = psdao.buscarPorLegajo(legajo1).get(0);
		plan.setPs(ps);
		Date date= new Date();
		plan.setFechaDePresentacion(date);
		planDeTrabajoDao.save(plan);
		registrarPresentacionInformeBean.setPsTitle(psTitle);
		registrarPresentacionInformeBean.setLegajo(0);
		registrarPresentacionInformeBean.setNombreAlumno("");
		registrarPresentacionInformeBean.updateTablaInformes();
		List<LineaTablaInformes> tablaInformes=registrarPresentacionInformeBean.getTablaInformes();
		assertEquals(tablaInformes.isEmpty(),false);
		assertEquals(tablaInformes.size()==2,true);
	}
	@Test
	public void succesSearchPsParaPresentarInformeByNombreAlumno() {
		registrarPresentacionInformeBean.setLegajo(0);
		registrarPresentacionInformeBean.setNombreAlumno(nombreAlumno);
		registrarPresentacionInformeBean.setPsTitle("");
		registrarPresentacionInformeBean.updateTablaInformes();
		List<LineaTablaInformes> tablaInformes=registrarPresentacionInformeBean.getTablaInformes();
		assertEquals(tablaInformes.isEmpty(),false);
		assertEquals(tablaInformes.size()==2,true);
	}
	@Test
	public void succesSearchPsParaPresentarInformeByLegajo() {
		registrarPresentacionInformeBean.setLegajo(legajo1);
		registrarPresentacionInformeBean.setNombreAlumno("");
		registrarPresentacionInformeBean.setPsTitle(psTitle);
		registrarPresentacionInformeBean.updateTablaInformes();
		List<LineaTablaInformes> tablaInformes = registrarPresentacionInformeBean.getTablaInformes();
		assertEquals(tablaInformes.isEmpty(),false);
		assertEquals(tablaInformes.size()==1,true);
	}
	/*
	public void successRegistrarPrsentacionInformePorLinea() {
		registrarPresentacionInformeBean.setLegajo(legajo2);
		registrarPresentacionInformeBean.setPsTitle("");
		registrarPresentacionInformeBean.setNombreAlumno("");
		registrarPresentacionInformeBean.updateTablaInformes();
		LineaTablaInformes linea=registrarPresentacionInformeBean.getTablaInformes().get(0);
		linea.setFechaPresentacion(new Date());
		registrarPresentacionInformeBean.registrarPresentacionInforme(linea);
		int idps = psdao.buscarPorLegajo(legajo2).get(0).getId();
		Estado estado = psdao.buscarPorLegajo(legajo2).get(0).getEstado(); 
		assertEquals(informeFinalDao.getByIdPs(idps).isEmpty(),false);
		assertEquals(estado.getId()==registrarPresentacionInformeBean.getIdEstadoInformePresentado(),true);
	}
	*/
	@Test
	public void successRegistrarPresentacionInformes() {
		registrarPresentacionInformeBean.setLegajo(legajo2);
		registrarPresentacionInformeBean.setPsTitle("");
		registrarPresentacionInformeBean.setNombreAlumno("");
		registrarPresentacionInformeBean.updateTablaInformes();
		LineaTablaInformes linea=registrarPresentacionInformeBean.getTablaInformes().get(0);
		linea.setFechaPresentacion(new Date());
		registrarPresentacionInformeBean.registrarPresentacionInformes();
		int idps = psdao.buscarPorLegajo(legajo2).get(0).getId();
		Estado estado = psdao.buscarPorLegajo(legajo2).get(0).getEstado();
		psdao.updateEstado(idps,registrarPresentacionInformeBean.getIdEstadoPlanAprobado());
		assertEquals(informeFinalDao.getByIdPs(idps).isEmpty(),false);
		assertEquals(estado.getId()==registrarPresentacionInformeBean.getIdEstadoInformePresentado(),true);
	}
}
