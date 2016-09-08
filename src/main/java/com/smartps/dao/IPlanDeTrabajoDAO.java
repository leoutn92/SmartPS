package com.smartps.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.smartps.model.PlanDeTrabajo;

public interface IPlanDeTrabajoDAO extends IGenericDAO<PlanDeTrabajo> {

	public List<PlanDeTrabajo> buscarPlanDeTrabajoByPeriodo(Date desde, Date hasta);
	
	public Set<PlanDeTrabajo> buscarPlanDeTrabajoByCicloLectivo(int CicloLectivo);
	
}
