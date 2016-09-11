package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;
import com.smartps.model.InformeFinal;
import com.smartps.util.HibernateUtil;

public class InformeFinalDao implements IGenericDAO<InformeFinal> {
	Session session= HibernateUtil.getSessionFactory().openSession();

	@Override
	public void save(InformeFinal informe) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.save(informe);
		session.getTransaction().commit();
	}

	@Override
	public void update(InformeFinal objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(InformeFinal objeto) {
		// TODO Auto-generated method stub
		
	}

	public List<InformeFinal> getByIdPs(int idps) {
		// TODO Auto-generated method stub
		List<InformeFinal> ifs = session.createQuery("SELECT f FROM InformeFinal f where f.ps.id= :idps")
				.setParameter("idps",idps).getResultList();
		return ifs;
	}
	
}
