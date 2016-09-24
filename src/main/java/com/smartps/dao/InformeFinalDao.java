package com.smartps.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Session;

import com.smartps.model.InformeFinal;
import com.smartps.util.HibernateUtil;


public class InformeFinalDao {

	
	Session session= HibernateUtil.getSessionFactory().openSession();

	public List<InformeFinal> retrieveAll(){
		List<InformeFinal> planes = (List<InformeFinal>) session
				.createQuery("SELECT p FROM InformeFinal p").getResultList();
		return planes;
	}
	
	public List<InformeFinal> findByPeriodo(Date desde, Date hasta){
		long dsdL = desde.getTime();
		long hstL = hasta.getTime();
		java.sql.Timestamp sqlTimestampD = new java.sql.Timestamp(dsdL);
		java.sql.Timestamp sqlTimestampH = new java.sql.Timestamp(hstL);
		
		List<InformeFinal> informes = (List<InformeFinal>) session
				.createQuery("SELECT p FROM InformeFinal p "
						+ "WHERE ((p.fechaDePresentacion > :desde) AND (p.fechaDePresentacion < :hasta))")
				.setParameter("desde", sqlTimestampD).setParameter("hasta", sqlTimestampH).getResultList();
		return informes;
	}
	
	public InformeFinal findByID(int id){
		InformeFinal pt = (InformeFinal) session.find(InformeFinal.class, id);
		return pt;
	}
	
	public void save(InformeFinal informe) {
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(informe);
		session.getTransaction().commit();
	}

	public void update(InformeFinal objeto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(InformeFinal objeto) {
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(objeto);
		session.getTransaction().commit();
	}

	public List<InformeFinal> getByIdPs(int idps) {
		session= HibernateUtil.getSessionFactory().openSession();
		List<InformeFinal> ifs = session.createQuery("SELECT f FROM InformeFinal f where f.ps.id= :idps")
				.setParameter("idps",idps).getResultList();
		return ifs;
	}
	



}
