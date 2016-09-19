package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;
import com.smartps.model.TipoActividad;

import com.smartps.util.HibernateUtil;

public class TipoActividadDAO {
	private static TipoActividadDAO instancia;
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	protected  TipoActividadDAO(){
		
	}
	
	public static TipoActividadDAO getInstance(){
		if (instancia == null){
			instancia= new TipoActividadDAO();
		}
		return instancia;
	}
	
	public List<TipoActividad> getAll(){
		return (session.createQuery("from TipoActividad").list());
		
		
		
	}
	

}
