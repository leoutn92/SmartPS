package com.smartps.dao;

import java.util.List;

import com.smartps.model.Alumno;
import com.smartps.model.PS;
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
	
	//devuelve la ps "vigente" mas reciente o null si no posee ninguna
	public PS getMostRecentPS(int legajo) {
		this.getSession();
		session.beginTransaction();
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and not( p.estado.id=5 or p.estado.id=9 or p.estado.id=11  or p.estado.id=10 or p.estado.id=4)" )
				.setParameter("legajo",legajo).getResultList();
		

		session.getTransaction().commit();
		if (pss.isEmpty()){
			return null;
		}
		return pss.get(pss.size()-1);
	}
	
	/**
	 * @param legajo
	 * @return verdadero si el alumno con el legajo del parámetro NO tiene alguna ps en los estados de 
	 * plan aprobado 3
	 * plan presentado 1
	 * informe presentado 6
	 * informe aprobado 8
	 * informe observado 7
	 * ps aprobada 10
	 */
	public boolean puedePresentarPlan(int legajo) {
		this.getSession();
		session.beginTransaction();
		List<PS> lista = session.createQuery(
				"from PS where alumno.legajo = :legajo "
				+ "and (estado.nombre ='Plan aprobado' or estado.nombre ='Plan presentado' or estado.nombre ='Informe presentado' or estado.nombre ='Informe aprobado' or estado.nombre ='Informe observado' or estado.nombre ='PS aprobada')" ).setParameter("legajo", legajo).getResultList();
		session.getTransaction().commit();
		return lista.size()==0;
	}
	
	/**
	 * NO son ps vigentes :
	 * plan rechazado
	 * plan vencido
	 * informe vencido
	 * ps cancelada
	 * ps aprobada
	 * 
	 * 
	 * @param legajo
	 * @return 
	 */
	public boolean tienePSVigente(int legajo) {
		this.getSession();
		session.beginTransaction();
		List<PS> lista = session.createQuery("from PS where alumno.legajo = :legajo and not estado.nombre ='PS cancelada' and not estado.nombre ='Informe vencido' and not estado.nombre ='Plan rechazado' and not estado.nombre ='Plan vencido' and not estado.nombre ='PS aprobada'" ).setParameter("legajo", legajo).getResultList();
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
	
	public boolean aproboPS(int legajo){
		this.getSession();
		session.beginTransaction();
		List<PS> lista = session.createQuery("from PS where alumno.legajo = :legajo and estado.nombre='PS aprobada'").setParameter("legajo",legajo).getResultList();
		session.getTransaction().commit();
		return lista.size()>0;
	}
	
}
