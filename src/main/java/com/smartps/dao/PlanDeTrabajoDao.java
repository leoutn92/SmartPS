package com.smartps.dao;

import java.util.Date;
import java.util.List;
import com.smartps.model.PlanDeTrabajo;

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
	
	public PlanDeTrabajo getLastByFechaPresentacion(int idps) {
		this.getSession();
		session.beginTransaction();
		List<PlanDeTrabajo> planes = session
		.createQuery("Select p from PlanDeTrabajo p where p.ps.id= :idps order by fechaDePresentacion desc ")
		.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		if (planes.isEmpty()) {
			return null;
		}
		return planes.get(0);
	}
	
	public PlanDeTrabajo getWithoutFechaEvaluacion(int idps) {
		this.getSession();
		session.beginTransaction();
		List<PlanDeTrabajo> planes = session
		.createQuery("Select p from PlanDeTrabajo p where ((p.ps.id= :idps) and (p.fechaAprobDesaprob is null))")
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
	
	public List<PlanDeTrabajo> getByIdPs(int idps) {
		this.getSession();
		session.beginTransaction();
		List<PlanDeTrabajo> pts = session.createQuery("SELECT f FROM PlanDeTrabajo f where f.ps.id= :idps")
				.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		return pts;
	}
	
	public PlanDeTrabajo findByID(int id){
		session.beginTransaction();
		PlanDeTrabajo pt = (PlanDeTrabajo) session.find(PlanDeTrabajo.class, id);
		session.getTransaction().commit();
		return pt;
	}
	
//	Devuelve un listado de los planes que han sido evaluados y aún no informados por email
	public List<PlanDeTrabajo> getNoInformados(){
		this.getSession();
		session.beginTransaction();
		List<PlanDeTrabajo> planes =session.createQuery("from PlanDeTrabajo p where p.fechaAprobDesaprob is not null and p.notificadoEmail=false").getResultList();
		session.getTransaction().commit();
		return planes;
	}
	
}