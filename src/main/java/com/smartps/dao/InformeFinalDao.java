package com.smartps.dao;

import java.util.List;
import java.util.Date;

import com.smartps.model.InformeFinal;
import com.smartps.util.HibernateUtil;


public class InformeFinalDao extends Dao<InformeFinal> {
	private static InformeFinalDao instancia = null;
	
	public InformeFinalDao(){
		super(InformeFinal.class);
	}
	
	public static InformeFinalDao getInstance(){
		if (instancia == null){
			instancia = new InformeFinalDao();
		}
		return instancia;
	}

	public List<InformeFinal> retrieveAll(){
		return this.getAll();
	}
	
	public List<InformeFinal> findByPeriodo(Date desde, Date hasta){
		this.getSession();
		session.beginTransaction();
		long dsdL = desde.getTime();
		long hstL = hasta.getTime();
		java.sql.Timestamp sqlTimestampD = new java.sql.Timestamp(dsdL);
		java.sql.Timestamp sqlTimestampH = new java.sql.Timestamp(hstL);
		
		List<InformeFinal> informes = (List<InformeFinal>) session
				.createQuery("SELECT p FROM InformeFinal p "
						+ "WHERE ((p.fechaDePresentacion > :desde) AND (p.fechaDePresentacion < :hasta))")
				.setParameter("desde", sqlTimestampD).setParameter("hasta", sqlTimestampH).getResultList();
		session.getTransaction().commit();
		return informes;
	}
	

	public List<InformeFinal> getByIdPs(int idps) {
		this.getSession();
		session.beginTransaction();
		List<InformeFinal> ifs = session.createQuery("SELECT f FROM InformeFinal f where f.ps.id= :idps")
				.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		return ifs;
	}
	



}