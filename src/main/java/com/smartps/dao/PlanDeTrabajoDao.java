package com.smartps.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.smartps.model.PlanDeTrabajo;
import com.smartps.util.HibernateUtil;

public class PlanDeTrabajoDao extends Dao<PlanDeTrabajo>{
	private static PlanDeTrabajoDao instancia=null;
	
	public PlanDeTrabajoDao() {
		super (PlanDeTrabajo.class);
	}
	
	public static PlanDeTrabajoDao getInstance(){
		if (instancia ==null){
			instancia=new PlanDeTrabajoDao();
		}
		return instancia;
	}

	
	public PlanDeTrabajo getLastByFechaAprobadoDesaprobado(int idps) {
		this.getSession();
		session.beginTransaction();
		List<PlanDeTrabajo> planes = session
		.createQuery("Select p from PlanDeTrabajo p where p.ps.id= :idps order by fechaAprobDesaprob desc ")
		.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		if (planes.isEmpty()) {
			return null;
		}
		return planes.get(0);
	}

	
	public List<PlanDeTrabajo> findByPeriodo(Date desde, Date hasta){
		this.getSession();
		session.beginTransaction();
		long dsdL = desde.getTime();
		long hstL = hasta.getTime();
		java.sql.Timestamp sqlTimestampD = new java.sql.Timestamp(dsdL);
		java.sql.Timestamp sqlTimestampH = new java.sql.Timestamp(hstL);
		
		List<PlanDeTrabajo> planes = (List<PlanDeTrabajo>) session
				.createQuery("SELECT p FROM PlanDeTrabajo p "
						+ "WHERE ((p.fechaDePresentacion > :desde) AND (p.fechaDePresentacion < :hasta))")
				.setParameter("desde", sqlTimestampD).setParameter("hasta", sqlTimestampH).getResultList();
		session.getTransaction().commit();
		return planes;
	}
	
	
}