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

public class PSDao implements IPSDao {
	Session session= HibernateUtil.getSessionFactory().openSession();
	EntityManager entitymanager;
	public void save(PS ps){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(ps);
		session.getTransaction().commit();
	}
	public List<PS> buscarPorLegajo(int legajo) {
		session =HibernateUtil.getSessionFactory().openSession();
		Integer newLegajo = legajo;
		List<PS> pss = (List<PS>) session.createQuery("Select p from PS p Where p.alumno.legajo= :legajo")
		.setParameter("legajo", newLegajo).getResultList();
		return pss;
	}
	public PS searchPs(int legajo, int idEstado) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().openSession();
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and  p.estado.id = :estado")
				.setParameter("legajo",legajo).setParameter("estado",idEstado).getResultList();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
		return pss.get(0);
	}
	public void updateEstado(int id, int idEstado) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.createQuery("update PS set estado.id = :idEstado where id = :idps")
				.setParameter("idps",id).setParameter("idEstado",idEstado).executeUpdate();
		session.getTransaction().commit();
	}
	public List<PS> searchPs(CriteriosParaFiltrarPs criterios, int idEstado) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().openSession();
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
		return pss;
	}
	public void delete(PS ps1) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(ps1);
		session.getTransaction().commit();
	}
	public void update(PS ps) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(ps);
		session.getTransaction().commit();
	}
	public PS getById(int id) {
		// TODO Auto-generated method stub
		session =HibernateUtil.getSessionFactory().openSession();
		return session.get(PS.class, id);
	}
}
