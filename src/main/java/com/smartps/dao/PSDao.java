package com.smartps.dao;
import java.util.List;

import com.smartps.beans.registrarPresentacionInforme.CriteriosParaFiltrarPs;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;

public class PSDao extends Dao<PS> {
	private static PSDao instancia = null;
	
	public PSDao(){
		super( PS.class);
	};
	
	public static PSDao getInstance(){
		if (instancia == null) {
			instancia = new PSDao();
		}
		return instancia;
	}
	
	public List<PS> buscarPorLegajo(int legajo) {
		this.getSession();
		session.beginTransaction();
		Integer newLegajo = legajo;
		List<PS> pss = (List<PS>) session.createQuery("Select p from PS p Where p.alumno.legajo= :legajo")
		.setParameter("legajo", newLegajo).getResultList();
		session.getTransaction().commit();
		return pss;
	}
	public PS searchPs(int legajo, int idEstado) {
		this.getSession();
		session.beginTransaction();
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and  p.estado.id = :estado")
				.setParameter("legajo",legajo).setParameter("estado",idEstado).getResultList();
		session.getTransaction().commit();
		return pss.get(0);
	}
	public void updateEstado(int id, int idEstado) {
		this.getSession();
		session.beginTransaction();
		session.createQuery("update PS set estado.id = :idEstado where id = :idps")
				.setParameter("idps",id).setParameter("idEstado",idEstado).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	public List<PS> searchPs(CriteriosParaFiltrarPs criterios, int idEstado) {
		this.getSession();
		session.beginTransaction();
		String queryBase = "Select p from PS p where p.estado.id = :idEstado ";
		List<PS> pss =(List<PS>) session.createQuery(queryBase)
				.setParameter("idEstado", idEstado).getResultList();
		if (criterios.getLegajo()==0) {
			if (criterios.getNombreAlumno()==null) {
				if (criterios.getPsTitle()!=null) {
					queryBase = queryBase  + " and p.titulo= :titulo ";
					pss = (List<PS>) session.createQuery(queryBase)
							.setParameter("idEstado", idEstado)
							.setParameter("titulo",criterios.getPsTitle()).getResultList();
				}
			} else {
			queryBase = queryBase  + " and p.alumno.nombre= :nombre ";
			pss = (List<PS>) session.createQuery(queryBase)
					.setParameter("idEstado", idEstado)
					.setParameter("nombre",criterios.getNombreAlumno()).getResultList();
			if (criterios.getPsTitle()!=null) {
				queryBase = queryBase  + " and p.titulo= :titulo ";
				pss = (List<PS>) session.createQuery(queryBase)
						.setParameter("idEstado", idEstado)
						.setParameter("nombre",criterios.getNombreAlumno())
						.setParameter("titulo",criterios.getPsTitle()).getResultList();
			}
			}
		} else {
			queryBase =  queryBase  + " and p.alumno.legajo = :legajo ";
			pss = (List<PS>) session.createQuery(queryBase)
					.setParameter("idEstado", idEstado)
					.setParameter("legajo",criterios.getLegajo()).getResultList();
			if (criterios.getNombreAlumno()==null) {
				if (criterios.getPsTitle()!=null) {
					queryBase = queryBase  + " and p.titulo= :titulo ";
					pss = (List<PS>) session.createQuery(queryBase)
							.setParameter("idEstado", idEstado)
							.setParameter("legajo",criterios.getLegajo())
							.setParameter("titulo",criterios.getPsTitle()).getResultList();
				}
			} else {
			queryBase = queryBase  + " and p.alumno.nombre= :nombre ";
			pss = (List<PS>) session.createQuery(queryBase)
					.setParameter("idEstado", idEstado)
					.setParameter("legajo",criterios.getLegajo())
					.setParameter("nombre",criterios.getNombreAlumno()).getResultList();
			if (criterios.getPsTitle()!=null) {
				queryBase = queryBase  + " and p.titulo= :titulo ";
				pss = (List<PS>) session.createQuery(queryBase)
						.setParameter("idEstado", idEstado)
						.setParameter("legajo",criterios.getLegajo())
						.setParameter("nombre",criterios.getNombreAlumno())
						.setParameter("titulo",criterios.getPsTitle()).getResultList();
			}
			}
			
		}
		session.getTransaction().commit();
		return pss;
	}
	
	public List<PS> findByCicloLectivo(int cicloLectivo) {	
		this.getSession();
		session.beginTransaction();
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cicloLectivo = :cicloLectivo")
				.setParameter("cicloLectivo", cicloLectivo).getResultList();
		session.getTransaction().commit();
		return pslist;
	}
	
	public List<PS> findByCuatrimestre(int cuatrimestre) {	
		this.getSession();
		session.beginTransaction();
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cuatrimestre = :cuatrimestre")
				.setParameter("cuatrimestre", cuatrimestre).getResultList();
		session.getTransaction().commit();
		return pslist;
	}
	
	public List<PS> findByLegajo(int legajo) {	
		this.getSession();
		session.beginTransaction();
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.legajo = :legajo")
				.setParameter("legajo", legajo).getResultList();
		session.getTransaction().commit();
		return pslist;
	}
	
}