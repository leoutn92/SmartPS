package com.smartps.dao;

import org.hibernate.Session;

import com.smartps.dao.IOrganizacionDAO;
import com.smartps.model.Organizacion;
import com.smartps.util.HibernateUtil;


public class OrganizacionDAO implements IOrganizacionDAO {

	
	Session session= HibernateUtil.getSessionFactory().openSession();

	
	public Organizacion findByID(int id){
		Organizacion org = (Organizacion) session.get(Organizacion.class, id);
		return org;
	}
	
	
	@Override
	public void save(Organizacion objeto) {
		// TODO Auto-generated method stub
		
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
