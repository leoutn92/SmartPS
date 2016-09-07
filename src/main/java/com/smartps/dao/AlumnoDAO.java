package com.smartps.dao;

//import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.smartps.util.HibernateUtil;
import com.smartps.model.Alumno;
import com.smartps.dao.IAlumnoDAO;

public class AlumnoDAO implements IAlumnoDAO {

	Session session= HibernateUtil.getSessionFactory().openSession();
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

	@Override
	public Alumno buscarAlumno(int legajo) {
		Alumno alu=(Alumno) session.get(Alumno.class,legajo);
		return alu;
	}
	
//	public List<Alumno> retriveALL(){}

	
}
