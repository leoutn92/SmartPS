package com.smartps.dao;

import com.smartps.model.Organizacion;

public interface IOrganizacionDAO extends IGenericDAO<Organizacion> {

	public Organizacion findByID(int id);
	
}
