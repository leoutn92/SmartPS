package com.smartps.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.smartps.model.Estado;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;

public class EstadoDao {
	private static EstadoDao instancia = null;
	Session session= HibernateUtil.getSessionFactory().openSession();

	protected EstadoDao(){};
	
	public static EstadoDao getInstance(){
		if (instancia==null){
			instancia = new EstadoDao();
		}
		return instancia;
	}
	
	public void save(Estado estado) {
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(estado);
		session.getTransaction().commit();
	}
	public void update(Estado estado) {
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(estado);
		session.getTransaction().commit();
	}
	public void delete(Estado objeto) {
		// TODO Auto-generated method stub
		
	}
	public Estado buscarPorNombre(String nombre) {
		session= HibernateUtil.getSessionFactory().openSession();
		List<Estado> estados = session.createQuery("SELECT e FROM Estado e where e.nombre= :nombre")
				.setParameter("nombre",nombre).getResultList();
		return estados.get(0);
	}
	public String getById(Estado estado) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getById(int id) {
		session= HibernateUtil.getSessionFactory().openSession();
		Estado estado = session.get(Estado.class, id);
		if (estado!=null) {
			return estado.getNombre();
		}
		return null;
	}
	
}
