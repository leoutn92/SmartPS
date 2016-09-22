package com.smartps.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Session;

import com.smartps.model.InformeFinal;
import com.smartps.util.HibernateUtil;
import com.smartps.dao.IInformeFinalDAO;


public class InformeFinalDAO implements IInformeFinalDAO {

	
	Session session= HibernateUtil.getSessionFactory().openSession();

	
	@Override
	public List<InformeFinal> retrieveAll(){
		List<InformeFinal> planes = (List<InformeFinal>) session
				.createQuery("SELECT p FROM InformeFinal p").getResultList();
		return planes;
	}
	
	@Override
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
	
	@Override
	public void save(InformeFinal objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(InformeFinal objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(InformeFinal objeto) {
		// TODO Auto-generated method stub

	}

}
