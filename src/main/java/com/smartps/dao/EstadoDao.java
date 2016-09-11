package com.smartps.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.smartps.model.Estado;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;

public class EstadoDao implements IGenericDAO<Estado> {

	private EntityManager entitymanager;
	Session session= HibernateUtil.getSessionFactory().openSession();
	@Override
	public void save(Estado estado) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.save(estado);
		session.getTransaction().commit();
	}
	@Override
	public void update(Estado objeto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Estado objeto) {
		// TODO Auto-generated method stub
		
	}
	public Estado buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		List<Estado> estados = session.createQuery("SELECT e FROM Estado e where e.nombre= :nombre")
				.setParameter("nombre",nombre).getResultList();
		return estados.get(0);
	}
	
}
