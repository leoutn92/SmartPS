package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Area;
import com.smartps.util.HibernateUtil;

public class AreaDao {
	private static AreaDao instancia=null;
	private Session session = HibernateUtil.getSessionFactory().openSession();

	protected AreaDao(){};
	
	public static AreaDao getInstance(){
		if(instancia ==null){
			instancia = new AreaDao();
		}
		return instancia;
	}
	
	public List<Area> getAll() {
		List<Area> areas= session.createQuery("from Area").getResultList();
		return areas;
	}
	
	public Area buscarArea(int id) {
		Area area= session.get(Area.class,id);
		return area;
}
	
	
	
	public void save(Object area) {
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(area);
		session.getTransaction().commit();
	}


	public void update(Object objeto) {
		
	}

	public void delete(Object objeto) {
		// TODO Auto-generated method stub
		
	}
	
}
