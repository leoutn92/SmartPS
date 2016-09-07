package com.smartps.dao;

//import java.util.List;
import javax.persistence.EntityManager;

import com.smartps.model.Alumno;
import com.smartps.dao.IAlumnoDAO;

public class AlumnoDAO implements IAlumnoDAO {

	EntityManager entitymanager;
	
	
	public void save(Alumno alumno){
		entitymanager.persist(alumno);
	}
	
	public void update(Alumno alumno){
		entitymanager.merge(alumno);
	}
	
	public void delete(Alumno alumno){
		entitymanager.remove(alumno);
	}
	
//	public List<Alumno> retriveALL(){}

	
}
