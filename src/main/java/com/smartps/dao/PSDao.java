package com.smartps.dao;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.smartps.beans.registrarPresentacionInforme.CriteriosParaFiltrarPs;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;

public class PSDao {
	Session session= HibernateUtil.getSessionFactory().getCurrentSession();
	private static PSDao instancia = null;
	
	protected PSDao(){};
	
	public static PSDao getInstance(){
		if (instancia == null) {
			instancia = new PSDao();
		}
		return instancia;
	}
	
	public void save(PS ps){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(ps);
		session.getTransaction().commit();
	}
	public List<PS> buscarPorLegajo(int legajo) {
		session =HibernateUtil.getSessionFactory().getCurrentSession();
		Integer newLegajo = legajo;
		List<PS> pss = (List<PS>) session.createQuery("Select p from PS p Where p.alumno.legajo= :legajo")
		.setParameter("legajo", newLegajo).getResultList();
		return pss;
	}
	public PS searchPs(int legajo, int idEstado) {
		session =HibernateUtil.getSessionFactory().getCurrentSession();
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and  p.estado.id = :estado")
				.setParameter("legajo",legajo).setParameter("estado",idEstado).getResultList();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
		return pss.get(0);
	}
	public void updateEstado(int id, int idEstado) {
		session =HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("update PS set estado.id = :idEstado where id = :idps")
				.setParameter("idps",id).setParameter("idEstado",idEstado).executeUpdate();
		session.getTransaction().commit();
	}
	public List<PS> searchPs(CriteriosParaFiltrarPs criterios, int idEstado) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().getCurrentSession();
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

	public PS getById(int id) {
		session =HibernateUtil.getSessionFactory().getCurrentSession();
		return session.get(PS.class, id);
	}

	public void update(PS objeto) {
		session =HibernateUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		session.update(objeto);
		session.getTransaction().commit();
		
	}
	
	// TODO no funciona este metodo
	public void delete(PS objeto) {
		session.beginTransaction();
		session.delete(objeto);
		session.getTransaction().commit();
		
	}
	
	public List<PS> getAll() {
		List<PS> lista = (List) session.createQuery("from PS").list();
		return lista;
	}

	public PS findById(int id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PS ps = (PS) session.get(PS.class, id);
		session.getTransaction().commit();
		return ps;
	}

	public List<PS> retrieveAll() {
		List<PS> pslist = (List<PS>) session
				.createQuery("SELECT p FROM PS p").getResultList();
		return pslist;
	}
	
	public List<PS> findByCicloLectivo(int cicloLectivo) {	
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cicloLectivo = :cicloLectivo")
				.setParameter("cicloLectivo", cicloLectivo).getResultList();
		return pslist;
	}
	
	public List<PS> findByCuatrimestre(int cuatrimestre) {	
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cuatrimestre = :cuatrimestre")
				.setParameter("cuatrimestre", cuatrimestre).getResultList();
		return pslist;
	}

	public List<PS> getByEstado(int idEstado) {
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PS> pslist = (List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.estado.id = :idEstado")
				.setParameter("idEstado", idEstado).getResultList();
		session.getTransaction().commit();
		return pslist;
	}
	
}
