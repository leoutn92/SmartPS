package com.smartps.dao;

import java.util.List;
import org.hibernate.Session;

import com.smartps.model.Area;
import com.smartps.util.HibernateUtil;

public class AreaDAO implements IArea {
	Session session= HibernateUtil.getSessionFactory().openSession();
	
	@Override
	public void save(Area objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Area objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Area objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Area> getAll() {
		List<Area> areas= session.createQuery("from Area").list();
		return areas;
	}
	
	public Area buscarArea(int id) {
		Area area=(Area) session.get(Area.class,id);
		return area;
	}

}
