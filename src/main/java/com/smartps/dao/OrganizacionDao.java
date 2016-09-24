package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Organizacion;
import com.smartps.util.HibernateUtil;

public class OrganizacionDao{
	private static OrganizacionDao instancia=null;
	Session session= HibernateUtil.getSessionFactory().openSession();


	
	protected OrganizacionDao(){}
	
	
	public static OrganizacionDao getInstance(){		
		if (instancia==null){
			instancia = new OrganizacionDao();
		}
		return instancia;		
	}

	public List<Organizacion> getAll(){
		List<Organizacion> org= session.createQuery("from Organizacion").list();
		return org;
		
	}


	
	public void save(Organizacion objeto) {
		session.beginTransaction();
		session.save(objeto);
		session.getTransaction().commit();
		
	}


	
	public void update(Organizacion objeto) {
		// TODO Auto-generated method stub
		
	}


	
	public void delete(Organizacion objeto) {
		session.beginTransaction();
		session.delete(objeto);
		session.getTransaction().commit();
		
	}


	public Organizacion getById(int id) {
		return session.get(Organizacion.class, id);
	}
	
	public Organizacion findByID(int id){
		Organizacion org = (Organizacion) session.get(Organizacion.class, id);
		return org;
	}
	
	
	
	
}