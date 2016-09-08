package com.smartps.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.smartps.util.HibernateUtil;
import com.smartps.model.Alumno;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;
import com.smartps.dao.IPlanDeTrabajoDAO;

public class PlanDeTrabajoDAO implements IPlanDeTrabajoDAO {


	Session session= HibernateUtil.getSessionFactory().openSession();
	EntityManager entitymanager;
	
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
	public List<PlanDeTrabajo> buscarPlanDeTrabajoByPeriodo(Date desde, Date hasta) {
		 
//		TypedQuery<PlanDeTrabajo> query = entitymanager.createQuery(
//			"SELECT g FROM PlanDeTrabajo g WHERE (g.fecha_presentacion > :dsd) AND (g.fecha_presentacion < :hst)", PlanDeTrabajo.class);
//		query.setParameter("dsd", desde);
//		query.setParameter("hst", hasta);
//		return query.getResultList();
		return null;
		 
	}

	@Override
	public Set<PlanDeTrabajo> buscarPlanDeTrabajoByCicloLectivo(int CicloLectivo) {
		PS ps = (PS) session.get(PS.class, CicloLectivo);
		Set<PlanDeTrabajo> planes = ps.getPlanDeTrabajo();		
		return planes;

	}
	

}
