package com.smartps.dao;

import java.util.List;

import com.smartps.model.Estado;

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
		this.getSession();
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
	public Estado getEstadoPlanPresentado() {
		return this.buscarPorNombre("Plan presentado");
	}
	public Estado getEstadoPlanObservado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Plan observado");
	};
	public Estado getEstadoPlanAprobado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Plan aprobado");
	}

	public Estado getEstadoInformePresentado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Informe presentado");
	}

	public Estado getEstadoInformeObservado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Informe observado");
	}

	public Estado getEstadoInformeAprobado() {
		// TODO Auto-generated method stub
		return this.buscarPorNombre("Informe aprobado");
	}
	
}
