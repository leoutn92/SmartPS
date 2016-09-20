package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.Estado;
import com.smartps.util.HibernateUtil;

public class EstadoDAO implements IEstadoDAO {

	Session session= HibernateUtil.getSessionFactory().openSession();


	@Override
	public List<Estado> findByNombre(String nombre) {
		List<Estado> est = (List<Estado>) session.createQuery("SELECT p FROM Estado p WHERE p.nombre = :nombre")
				.setParameter("nombre", nombre).getResultList();
		return est;
	}
	
	@Override
	public void save(Estado objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Estado objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Estado objeto) {
		// TODO Auto-generated method stub

	}

}
