package com.smartps.dao;

import java.util.List;
import java.util.Date;

import com.smartps.model.InformeFinal;

public interface IInformeFinalDAO extends IGenericDAO<InformeFinal> {

	List<InformeFinal> retrieveAll();
	
	List<InformeFinal> findByPeriodo(Date desde, Date hasta);

	InformeFinal findByID(int id);
		
}
