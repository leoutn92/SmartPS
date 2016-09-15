package com.smartps.dao;

import java.util.List;

import com.smartps.model.PS;


public interface IPSDAO extends IGenericDAO<PS> {

	PS findById(int id);
	
	List<PS> findByCicloLectivo(int cicloLectivo);
	
	List<PS> findByCuatrimestre(int cuatrimestre);
	
}
