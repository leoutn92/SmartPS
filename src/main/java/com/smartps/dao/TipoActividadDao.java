package com.smartps.dao;

import org.hibernate.Session;

import com.smartps.dao.IGenericDAO;
import com.smartps.model.TipoActividad;
import com.smartps.util.HibernateUtil;

public class TipoActividadDao implements IGenericDAO<TipoActividad> {
	Session session= HibernateUtil.getSessionFactory().openSession();
	@Override
	public void save(TipoActividad tActividad) {
		
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(tActividad);
		session.getTransaction().commit();
	}

	@Override
	public void update(TipoActividad objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TipoActividad objeto) {
		// TODO Auto-generated method stub
		
	}
	
}
