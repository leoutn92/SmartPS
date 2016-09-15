package com.smartps.dao;

import java.util.List;
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
	
	public List<PS> findByCuatrimestre(int cuatrimestre) {	
		List<PS> pslist=(List<PS>) session.createQuery("SELECT p FROM PS p WHERE p.cuatrimestre = :cuatrimestre")
				.setParameter("cuatrimestre", cuatrimestre).getResultList();
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
