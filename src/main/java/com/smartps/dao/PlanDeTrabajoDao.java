package com.smartps.dao;

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

	@Override
	public void delete(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub

	}

}
