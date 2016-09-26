package com.smartps.dao;

import java.util.List;

//import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.smartps.util.HibernateUtil;
import com.smartps.model.Alumno;
import com.smartps.model.PS;

public class AlumnoDAO {
	private static AlumnoDAO instancia = null;
	Session session= HibernateUtil.getSessionFactory().openSession();
	
	protected AlumnoDAO(){};

	public static AlumnoDAO getInstance(){
		if (instancia ==null){
			instancia = new AlumnoDAO();
		}
		return instancia;
	}
	
	public void save(Alumno alumno){
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(alumno);
		session.getTransaction().commit();
	}
	
	public PS getMostRecentPS(int legajo) {
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and not( p.estado.nombre = 'Plan vencido') or p.estado.nombre = 'Informe vencido' or p.estado.nombre = 'PS cancelada'  or p.estado.nombre = 'PS aprobada' or p.estado.nombre = 'Plan rechazado'" )
				.setParameter("legajo",legajo).getResultList();
		
		return pss.get(0);
	}
	
	public void update(Alumno alumno){
		session= HibernateUtil.getSessionFactory().openSession();
	}
	
	public void delete(Alumno alumno){
		session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(alumno);
		session.getTransaction().commit();
	}

	public Alumno buscarAlumno(int legajo) {
		session= HibernateUtil.getSessionFactory().openSession();
		Alumno alu=(Alumno) session.get(Alumno.class,legajo);
		return alu;
	}

	public boolean puedePresentarPlan(int legajo) {
		List<PS> lista = session.createQuery(
				"from PS where alumno.legajo = :legajo "
				+ "and (estado.nombre ='Plan aprobado' or estado.nombre ='Plan presentado' or estado.nombre ='Informe presentado' or estado.nombre ='Informe aprobado' or estado.nombre ='Informe observado' or estado.nombre ='PS aprobada')" ).setParameter("legajo", legajo).getResultList();
		return lista.size()==0;
	}

	public boolean tienePSVigente(int legajo) {
		List<PS> lista = session.createQuery("from PS where alumno.legajo = :legajo and not estado.nombre ='PS cancelada' and not estado.nombre ='PS aprobada' and not estado.nombre ='Informe vencido' and not estado.nombre ='Plan rechazado' and not estado.nombre ='Plan vencido'" ).setParameter("legajo", legajo).getResultList();
		return lista.size()>0;
	}

	
}
