package com.smartps.dao;

import java.util.Date;

import com.smartps.model.PlanDeTrabajo;

public interface IPlanDeTrabajoDAO extends IGenericDAO<PlanDeTrabajo> {

	public PlanDeTrabajo buscarPlanDeTrabajoByPeriodo(Date desde, Date hasta);
	
	public PlanDeTrabajo buscarPlanDeTrabajoByCicloLectivo(int CicloLectivo);
	
}
