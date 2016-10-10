package com.smartps.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.HibernateUtil;

public class PlanDeTrabajoDao {
	private static PlanDeTrabajoDao instancia=null;
	private Session session =HibernateUtil.getSessionFactory().getCurrentSession();
	
	protected PlanDeTrabajoDao() {	}
	
	public static PlanDeTrabajoDao getInstance(){
		if (instancia ==null){
			instancia=new PlanDeTrabajoDao();
		}
		return instancia;
	}

	public void save(PlanDeTrabajo objeto) {
		session.beginTransaction();
		session.save(objeto);
		session.getTransaction().commit();
	}

	public void update(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub
		session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(objeto);
		session.getTransaction().commit();
	}
	
	public PlanDeTrabajo getLastByFechaAprobadoDesaprobado(int idps) {
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PlanDeTrabajo> planes = session
		.createQuery("Select p from PlanDeTrabajo p where p.ps.id= :idps order by fechaAprobDesaprob desc ")
		.setParameter("idps",idps).getResultList();
		if (planes.isEmpty()) {
			return null;
		}
		session.getTransaction().commit();
		return planes.get(0);
	}

		public void delete(PlanDeTrabajo objeto) {
			session= HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(objeto);
		}


	
	public List<PlanDeTrabajo> retrieveAll(){
		List<PlanDeTrabajo> planes = (List<PlanDeTrabajo>) session
				.createQuery("SELECT p FROM PlanDeTrabajo p").getResultList();
		return planes;
	}
	
	public List<PlanDeTrabajo> findByPeriodo(Date desde, Date hasta){
		long dsdL = desde.getTime();
		long hstL = hasta.getTime();
		java.sql.Timestamp sqlTimestampD = new java.sql.Timestamp(dsdL);
		java.sql.Timestamp sqlTimestampH = new java.sql.Timestamp(hstL);
		
		List<PlanDeTrabajo> planes = (List<PlanDeTrabajo>) session
				.createQuery("SELECT p FROM PlanDeTrabajo p "
						+ "WHERE ((p.fechaDePresentacion > :desde) AND (p.fechaDePresentacion < :hasta))")
				.setParameter("desde", sqlTimestampD).setParameter("hasta", sqlTimestampH).getResultList();
		return planes;
	}
	
	public PlanDeTrabajo findByID(int id){
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PlanDeTrabajo pt = (PlanDeTrabajo) session.find(PlanDeTrabajo.class, id);
		session.getTransaction().commit();
		return pt;
	}
	
}
