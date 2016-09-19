package com.smartps.dao;

import org.hibernate.Session;

import com.smartps.util.HibernateUtil;

public class AreaDao implements IGenericDAO {
	Session session= HibernateUtil.getSessionFactory().openSession();
	@Override
	public void save(Object area) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.save(area);
		session.getTransaction().commit();
	}

	@Override
	public void update(Object objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object objeto) {
		// TODO Auto-generated method stub
		
	}
	
}
