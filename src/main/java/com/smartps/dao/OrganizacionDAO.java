package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Organizacion;
import com.smartps.util.HibernateUtil;

public class OrganizacionDAO {
	private static OrganizacionDAO instancia=null;
	Session session= HibernateUtil.getSessionFactory().openSession();
	
	protected OrganizacionDAO(){}
	
	
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
	
	
}
