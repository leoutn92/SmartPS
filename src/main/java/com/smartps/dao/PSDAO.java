package com.smartps.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Session;

import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;


public class PSDAO implements IPSDAO {

	Session session= HibernateUtil.getSessionFactory().openSession();
	
	
	@Override
	public PS findById(int id) {
		PS ps = (PS) session.get(PS.class, id);
		return ps;
	}
	
	@Override
	public List<PS> findByCicloLectivo(int cicloLectivo) {	
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cicloLectivo = :cicloLectivo")
				.setParameter("cicloLectivo", cicloLectivo).getResultList();
		return pslist;
	}
	
	@Override
	public List<PS> findByCuatrimestre(int cuatrimestre) {	
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cuatrimestre = :cuatrimestre")
				.setParameter("cuatrimestre", cuatrimestre).getResultList();
		return pslist;
	}
	
	@Override
	public List<PS> findByPeriodo(Date desde, Date hasta) {
		List<PS> pslist=(List<PS>) session
				.createQuery("SELECT s FROM PS s INNER JOIN PlanDeTrabajo t ON (s.id_ps=t.id_ps)"
						+ "WHERE (t.fecha_presentacion > :desde) AND (t.fecha_presentacion < :hasta)")
				.setParameter("desde", desde).setParameter("hasta", hasta).getResultList();		
		return pslist;
	}
	
	
	@Override
	public void save(PS objeto) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(PS objeto) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(PS objeto) {
		// TODO Auto-generated method stub
	}

}
