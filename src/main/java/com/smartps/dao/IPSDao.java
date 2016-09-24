package com.smartps.dao;

import java.util.List;

import com.smartps.model.PS;

public interface IPSDao extends IGenericDAO<PS> {
	public List<PS> buscarPorLegajo(int legajo);
	public List<PS> getAll();
	public void updateEstado(int id, int idEstado);
}
