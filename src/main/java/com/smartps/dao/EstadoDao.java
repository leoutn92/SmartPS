package com.smartps.dao;

import java.util.List;

import com.smartps.model.Estado;
import com.smartps.util.HibernateUtil;

public class EstadoDao extends Dao<Estado> {
	private static EstadoDao instancia = null;

	public EstadoDao(){
		super(Estado.class);
	};
	
	public static EstadoDao getInstance(){
		if (instancia==null){
			instancia = new EstadoDao();
		}
		return instancia;
	}
	
	public Estado buscarPorNombre(String nombre) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Estado> estados = session.createQuery("SELECT e FROM Estado e where e.nombre= :nombre")
				.setParameter("nombre",nombre).getResultList();
		session.getTransaction().commit();
		return estados.get(0);	
	}
	

	public String getNameById(int id) {
		Estado estado = getById(id);
		if (estado!=null) {
			return estado.getNombre();
		}
		return null;
	}
	
}