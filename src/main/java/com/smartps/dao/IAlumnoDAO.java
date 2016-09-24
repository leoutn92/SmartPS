package com.smartps.dao;


import com.smartps.model.Alumno;

public interface IAlumnoDAO extends IGenericDAO<Alumno> {

	public Alumno buscarAlumno(int legajo);
	public boolean puedePresentarPlan(int legajo);
	public boolean tienePSVigente(int legajo);
}
