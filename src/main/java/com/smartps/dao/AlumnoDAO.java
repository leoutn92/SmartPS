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
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(alumno);
		session.getTransaction().commit();
	}
	
	public void update(Alumno alumno){
		session= HibernateUtil.getSessionFactory().openSession();
		entitymanager.merge(alumno);
	}
	
	public void delete(Alumno alumno){
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(alumno);
		session.getTransaction().commit();
	}

	@Override
	public Alumno buscarAlumno(int legajo) {
		session= HibernateUtil.getSessionFactory().openSession();
		Alumno alu=(Alumno) session.get(Alumno.class,legajo);
		return alu;
	}
	
//	public List<Alumno> retriveALL(){}

	
}
