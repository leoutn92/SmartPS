package com.smartps.dao;

import java.util.List;

import com.smartps.model.Alumno;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;
import com.smartps.dao.Dao;


public class AlumnoDAO extends Dao<Alumno> {
//	private static AlumnoDAO instancia = null;
	
	public AlumnoDAO(){
		super( Alumno.class);
	};

//	public static AlumnoDAO getInstance(){
//		if (instancia ==null){
//			instancia = new AlumnoDAO();
//		}
//		return instancia;
//	}
	
	
	public PS getMostRecentPS(int legajo) {
		this.getSession();
		session.beginTransaction();
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and not( p.estado.nombre = 'Plan vencido') or p.estado.nombre = 'Informe vencido' or p.estado.nombre = 'PS cancelada'  or p.estado.nombre = 'PS aprobada' or p.estado.nombre = 'Plan rechazado'" )
				.setParameter("legajo",legajo).getResultList();
		
		if (pss.isEmpty()){
			pss=null;
		}
		session.getTransaction().commit();
		return pss.get(0);
	}
	

	public boolean puedePresentarPlan(int legajo) {
		this.getSession();
		session.beginTransaction();
		List<PS> lista = session.createQuery(
				"from PS where alumno.legajo = :legajo "
				+ "and (estado.nombre ='Plan aprobado' or estado.nombre ='Plan presentado' or estado.nombre ='Informe presentado' or estado.nombre ='Informe aprobado' or estado.nombre ='Informe observado' or estado.nombre ='PS aprobada')" ).setParameter("legajo", legajo).getResultList();
		session.getTransaction().commit();
		return lista.size()==0;
	}

	public boolean tienePSVigente(int legajo) {
		this.getSession();
		session.beginTransaction();
		List<PS> lista = session.createQuery("from PS where alumno.legajo = :legajo and not estado.nombre ='PS cancelada' and not estado.nombre ='PS aprobada' and not estado.nombre ='Informe vencido' and not estado.nombre ='Plan rechazado' and not estado.nombre ='Plan vencido'" ).setParameter("legajo", legajo).getResultList();
		session.getTransaction().commit();
		return lista.size()>0;
	}

	public List<Alumno> findByIngreso(int cicloLectivo) {	
		this.getSession();
		session.beginTransaction();
		List<Alumno> alumnlist=(List<Alumno>) session.createQuery("SELECT p FROM Alumno p WHERE p.cicloLectivo = :cicloLectivo")
				.setParameter("cicloLectivo", cicloLectivo).getResultList();
		session.getTransaction().commit();
		return alumnlist;
	}
	
}
