package com.smartps.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Session;

import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.HibernateUtil;
import com.smartps.dao.IPlanDeTrabajoDAO;


public class PlanDeTrabajoDAO implements IPlanDeTrabajoDAO {

	
	Session session= HibernateUtil.getSessionFactory().openSession();

	
	@Override
	public List<PlanDeTrabajo> retrieveAll(){
		List<PlanDeTrabajo> planes = (List<PlanDeTrabajo>) session
				.createQuery("SELECT p FROM PlanDeTrabajo p").getResultList();
		return planes;
	}
	
	@Override
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
		PlanDeTrabajo pt = (PlanDeTrabajo) session.find(PlanDeTrabajo.class, id);
		return pt;
	}
	
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

}
