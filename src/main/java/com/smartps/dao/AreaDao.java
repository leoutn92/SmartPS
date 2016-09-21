package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Area;
import com.smartps.util.HibernateUtil;

public class AreaDao implements IGenericDAO {

	private Session session = HibernateUtil.getSessionFactory().openSession();
		
	public List<Area> getAll() {
		List<Area> areas= session.createQuery("from Area").list();
		return areas;
	}
	
	public Area buscarArea(int id) {
		Area area=(Area) session.get(Area.class,id);
		return area;
}
	
	
	@Override
	public void save(Object area) {
		// TODO Auto-generated method stub
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
