package com.smartps.dao;

import java.util.List;

import com.smartps.model.Organizacion;

public interface IOrganizacioDao extends IGenericDAO<Organizacion> {

	public List<Organizacion> getAll();
	public Organizacion getById(int id);
	
}
