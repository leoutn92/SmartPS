package com.smartps.dao;

import java.util.List;

import com.smartps.model.PS;

public interface IPSDao {
	public List<PS> buscarPorLegajo(int legajo);
	public void registrarInforme(PS ps);
}
