package com.smartps.dao;

import java.util.List;

import com.smartps.model.Estado;

public interface IEstadoDAO extends IGenericDAO<Estado> {

	List<Estado> findByNombre(String nombre);
	
}
