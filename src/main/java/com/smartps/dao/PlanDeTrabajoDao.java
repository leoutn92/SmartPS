package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.HibernateUtil;

public class PlanDeTrabajoDao implements IPlanDeTrabajoDao {
	private static PlanDeTrabajoDao instancia=null;
	private Session session =HibernateUtil.getSessionFactory().openSession();
	
	protected PlanDeTrabajoDao() {	}
	
	public static PlanDeTrabajoDao getInstance(){
		if (instancia ==null){
			instancia=new PlanDeTrabajoDao();
		}
		return instancia;
	}

	@Override
	public void save(PlanDeTrabajo objeto) {
		session.beginTransaction();
		session.save(objeto);
		session.getTransaction().commit();
	}

	@Override
	public void update(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub

	}
	
	public PlanDeTrabajo getLastByFechaAprobadoDesaprobado(int idps) {
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().openSession();
		List<PlanDeTrabajo> planes = session
		.createQuery("Select p from PlanDeTrabajo p where p.ps.id= :idps order by fechaAprobDesaprob desc ")
		.setParameter("idps",idps).getResultList();
		if (planes.isEmpty()) {
			return null;
		}
		return planes.get(0);
	}

		
		@Override
		public void delete(PlanDeTrabajo objeto) {
			// TODO Auto-generated method stub
			session= HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(objeto);
		}




}
