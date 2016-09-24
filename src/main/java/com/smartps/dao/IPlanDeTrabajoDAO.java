package com.smartps.dao;

import java.util.List;
import java.util.Date;

import com.smartps.model.PlanDeTrabajo;

public interface IPlanDeTrabajoDAO extends IGenericDAO<PlanDeTrabajo> {

	List<PlanDeTrabajo> retrieveAll();
	
	List<PlanDeTrabajo> findByPeriodo(Date desde, Date hasta);

	PlanDeTrabajo findByID(int id);
		
}
