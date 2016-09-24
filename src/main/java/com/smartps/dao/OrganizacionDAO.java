package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Organizacion;
import com.smartps.util.HibernateUtil;

public class OrganizacionDAO implements IOrganizacioDao{
	private static OrganizacionDAO instancia=null;
	Session session= HibernateUtil.getSessionFactory().openSession();


	
//	protected OrganizacionDAO(){}
	
	
	public static OrganizacionDAO getInstance(){		
		if (instancia==null){
			instancia = new OrganizacionDAO();
		}
		return instancia;		
	}

	public List<Organizacion> getAll(){
		List<Organizacion> org= session.createQuery("from Organizacion").list();
		return org;
		
	}


	@Override
	public void save(Organizacion objeto) {
		session.beginTransaction();
		session.save(objeto);
		session.getTransaction().commit();
		
	}


	@Override
	public void update(Organizacion objeto) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Organizacion objeto) {
		session.beginTransaction();
		session.delete(objeto);
		session.getTransaction().commit();
		
	}


	@Override
	public Organizacion getById(int id) {
		return session.get(Organizacion.class, id);
	}
	
	
}
