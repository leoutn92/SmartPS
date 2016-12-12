package com.smartps.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.smartps.model.Estado;
import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.model.PlanDeTrabajo;

public class InformeFinalDao extends Dao<InformeFinal> {
	private static InformeFinalDao instancia = null;
	
	public InformeFinalDao(){
		super(InformeFinal.class);
	}
	
	public static InformeFinalDao getInstance(){
		if (instancia == null){
			instancia = new InformeFinalDao();
		}
		return instancia;
	}

	
	public List<InformeFinal> findByPeriodo(Date desde, Date hasta){
		this.getSession();
		session.beginTransaction();
		long dsdL = desde.getTime();
		long hstL = hasta.getTime();
		java.sql.Timestamp sqlTimestampD = new java.sql.Timestamp(dsdL);
		java.sql.Timestamp sqlTimestampH = new java.sql.Timestamp(hstL);
		
		List<InformeFinal> informes = (List<InformeFinal>) session
				.createQuery("SELECT p FROM InformeFinal p "
						+ "WHERE ((p.fechaDePresentacion > :desde) AND (p.fechaDePresentacion < :hasta))")
				.setParameter("desde", sqlTimestampD).setParameter("hasta", sqlTimestampH).getResultList();
		session.getTransaction().commit();
		return informes;
	}
	

	public List<InformeFinal> getByIdPs(int idps) {
		this.getSession();
		session.beginTransaction();
		List<InformeFinal> ifs = session.createQuery("SELECT f FROM InformeFinal f where f.ps.id= :idps")
				.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		return ifs;
	}

	public InformeFinal getLastByFechaAprobadoDesaprobado(int idps) {
		// TODO Auto-generated method stub
		this.getSession();
		session.beginTransaction();
		List<InformeFinal> informes = session
		.createQuery("Select i from InformeFinal i where i.ps.id= :idps order by fechaAprobDesaprob desc ")
		.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		if (informes.isEmpty()) {
			return null;
		}
		return informes.get(0);
	}

	public InformeFinal findById(int idInforme) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		InformeFinal informeFinal = (InformeFinal) session.get(InformeFinal.class, idInforme);
		session.getTransaction().commit();
		return informeFinal;
	}

	public InformeFinal getLastByFechaPresentacion(int idps) {
		// TODO Auto-generated method stub
		this.getSession();
		session.beginTransaction();
		List<InformeFinal> informes = session
		.createQuery("Select i from InformeFinal i where i.ps.id= :idps order by fechaDePresentacion desc ")
		.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		if (informes.isEmpty()) {
			return null;
		}
		return informes.get(0);
	}
	
	public InformeFinal getWithoutFechaEvaluacion(int idps) {
		// TODO Auto-generated method stub
		this.getSession();
		session.beginTransaction();
		List<InformeFinal> informes = session
		.createQuery("Select i from InformeFinal i where ((i.ps.id= :idps) and (i.fechaAprobDesaprob is null))")
		.setParameter("idps",idps).getResultList();
		session.getTransaction().commit();
		if (informes.isEmpty()) {
			return null;
		}
		return informes.get(0);
	}

	//Devuelve un listado de los informes que han sido evaluados y aún no informados por email
	public List<InformeFinal> getNoInformados(){
		this.getSession();
		session.beginTransaction();
		List<InformeFinal> informes =session.createQuery("from InformeFinal p where p.fechaAprobDesaprob is not null and p.notificadoEmail=false").getResultList();
		session.getTransaction().commit();
		return informes;
	}
	
	//Devuelve el último informe de cada PS en estado de INforme aprobado
		@SuppressWarnings("unchecked")
		public List<InformeFinal> getUltimosConPSInformeApobado(){
			this.getSession();
			session.beginTransaction();
			List <PS> pss = session.createQuery("from PS p where p.estado.id=8 ").getResultList();
			session.getTransaction().commit();;
			
			List<InformeFinal> informes = new ArrayList<>();
			for (PS ps : pss){
				InformeFinal inf;
				session.beginTransaction();
				inf= (InformeFinal) session.createQuery("from InformeFinal p where p.ps.id=:ps order by p.id desc").setParameter("ps", ps.getId()).getResultList().get(0);
				session.getTransaction().commit();;
				informes.add(inf);
			}
			
			return informes;
		}

}