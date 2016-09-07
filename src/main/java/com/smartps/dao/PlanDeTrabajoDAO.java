package com.smartps.dao;

import org.hibernate.Session;

import java.util.Date;

import com.smartps.util.HibernateUtil;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.dao.IPlanDeTrabajoDAO;

public class PlanDeTrabajoDAO implements IPlanDeTrabajoDAO {


	Session session= HibernateUtil.getSessionFactory().openSession();
	
	
	@Override
	public void save(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(PlanDeTrabajo objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public PlanDeTrabajo buscarPlanDeTrabajoByPeriodo(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanDeTrabajo buscarPlanDeTrabajoByCicloLectivo(int CicloLectivo) {
		// TODO Auto-generated method stub
		return null;
	}

}
