package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Area;
import com.smartps.util.HibernateUtil;

public class AreaDao implements IGenericDAO {
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
	
	
	@Override
	public void save(Object area) {
		// TODO Auto-generated method stub
		session= HibernateUtil.getSessionFactory().openSession();
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