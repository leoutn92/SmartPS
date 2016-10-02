package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Organizacion;
import com.smartps.util.HibernateUtil;

public class OrganizacionDao extends Dao<Organizacion>{
	private static OrganizacionDao instancia =null;


	
	public OrganizacionDao(){
		super(Organizacion.class);
	}
	
	
	public static OrganizacionDao getInstance(){		
		if (instancia==null){
			instancia = new OrganizacionDao();
		}
		return instancia;		
	}

	
	
	
	
}