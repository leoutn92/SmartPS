package com.smartps.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;

public class PSDao implements IPSDao {
	public SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public Session session= sessionFactory.openSession();
	EntityManager entitymanager;
	
	
	public void save(PS ps){
		session.beginTransaction();
		session.save(ps);
		session.getTransaction().commit();
		session.close();
		
	}
	public List<PS> buscarPorLegajo(int legajo) {
		Session session =sessionFactory.openSession();
		List<PS> ps =(List<PS>) session.createQuery("SELECT * FROM com.smartps.model.Ps PS WHERE PS.legajo=legajo");
		session.clear();
		return ps;
	}
	public void registrarInforme(PS ps) {
		// TODO Auto-generated method stub
		Session session =sessionFactory.openSession();
	}
	
}
