package com.smartps.dao;

import java.util.List;

//import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.smartps.util.HibernateUtil;
import com.smartps.model.Alumno;
import com.smartps.model.PS;
import com.smartps.dao.IAlumnoDAO;

public class AlumnoDAO implements IAlumnoDAO {

	Session session= HibernateUtil.getSessionFactory().openSession();
	EntityManager entitymanager;
	
	
	public void save(Alumno alumno){
		session.beginTransaction();
		session.save(alumno);
		session.getTransaction().commit();
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

	@Override
	public boolean puedePresentarPlan(int legajo) {
		List<PS> lista = session.createQuery(
				"from PS where alumno.legajo = :legajo "
				+ "and (estado.nombre ='Plan aprobado' or estado.nombre ='Plan presentado' or estado.nombre ='Informe presentado' or estado.nombre ='Informe aprobado' or estado.nombre ='Informe observado' or estado.nombre ='PS aprobada')" ).setParameter("legajo", legajo).getResultList();
		return lista.size()==0;
	}

	@Override
	public boolean tienePSVigente(int legajo) {
		List<PS> lista = session.createQuery("from PS where alumno.legajo = :legajo and not estado.nombre ='PS cancelada' and not estado.nombre ='PS aprobada' and not estado.nombre ='Informe vencido' and not estado.nombre ='Plan rechazado' and not estado.nombre ='Plan vencido'" ).setParameter("legajo", legajo).getResultList();
		return lista.size()>0;
	}

	
}
