package com.smartps.dao;

import java.util.List;
import org.hibernate.Session;
import com.smartps.model.Estado;
import com.smartps.util.HibernateUtil;

public class EstadoDao {
	private static EstadoDao instancia = null;
	Session session= HibernateUtil.getSessionFactory().getCurrentSession();

	protected EstadoDao(){};
	
	public static EstadoDao getInstance(){
		if (instancia==null){
			instancia = new EstadoDao();
		}
		return instancia;
	}
	
	public void save(Estado estado) {
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(estado);
		session.getTransaction().commit();
	}
	public void update(Estado estado) {
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(estado);
		session.getTransaction().commit();
	}
	public void delete(Estado objeto) {
		// TODO Auto-generated method stub
		
	}
	public Estado buscarPorNombre(String nombre) {
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Estado> estados = session.createQuery("SELECT e FROM Estado e where e.nombre= :nombre")
				.setParameter("nombre",nombre).getResultList();
		session.getTransaction().commit();
		return estados.get(0);
	}
	public String getById(Estado estado) {
		return null;
	}
	public String getById(int id) {
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		Estado estado = session.get(Estado.class, id);
		if (estado!=null) {
			return estado.getNombre();
		}
		return null;
	}

	public Estado getEstadoPlanPresentado() {
		return this.buscarPorNombre("Plan presentado");
	}

	public Estado getEstadoPlanAprobado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Plan aprobado");
	}

	public Estado getEstadoPlanDesaprobado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Plan desaprobado");
	}

	public Estado getEstadoPlanObservado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Plan observado");
	};
}
