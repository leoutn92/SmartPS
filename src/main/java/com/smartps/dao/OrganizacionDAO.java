package com.smartps.dao;

import org.hibernate.Session;

import com.smartps.model.Organizacion;
import com.smartps.util.HibernateUtil;

public class OrganizacionDAO implements IGenericDAO<Organizacion> {
	Session session= HibernateUtil.getSessionFactory().openSession();
	@Override
	public void save(Organizacion org) {
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(org);
		session.getTransaction().commit();
	}

	@Override
	public void update(Organizacion objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Organizacion objeto) {
		// TODO Auto-generated method stub
		
	}
	

}
