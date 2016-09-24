package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.HibernateUtil;

public class PlanDeTrabajoDao implements IGenericDAO<PlanDeTrabajo> {
	Session session= HibernateUtil.getSessionFactory().openSession();
	@Override
	public void save(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(objeto);
	}

	@Override
	public void update(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(objeto);
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

}
