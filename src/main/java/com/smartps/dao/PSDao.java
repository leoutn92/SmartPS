package com.smartps.dao;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.smartps.model.InformeFinal;
import com.smartps.model.PS;
import com.smartps.util.HibernateUtil;

public class PSDao implements IPSDao {
	Session session= HibernateUtil.getSessionFactory().openSession();
	EntityManager entitymanager;
	public void save(PS ps){
		session.beginTransaction();
		session.save(ps);
		session.getTransaction().commit();
	}
	public List<PS> buscarPorLegajo(int legajo) {
		Integer newLegajo = legajo;
		List<PS> pss = (List<PS>) entitymanager.createQuery("Select Ps from Ps Where Ps.legajo=legajo")
		.setParameter("legajo", newLegajo).getResultList();
		return pss;
	}
	public PS searchPs(int legajo, int idEstado) {
		List<PS> pss =(List<PS>) session.createQuery("SELECT p FROM PS p where p.alumno.legajo = :legajo and  p.estado.id = :estado")
				.setParameter("legajo",legajo).setParameter("estado",idEstado).getResultList();
		return pss.get(0);
	}
	public void updateEstado(int id, int idEstado) {
		session.beginTransaction();
		session.createQuery("update PS set estado.id = :idEstado where id = :idps")
				.setParameter("idps",id).setParameter("idEstado",idEstado).executeUpdate();
		session.getTransaction().commit();
	}
	@Override
	public void update(PS objeto) {
		session.beginTransaction();
		session.update(objeto);
		session.getTransaction().commit();
		
	}
	
	// TODO no funciona este metodo
	@Override
	public void delete(PS objeto) {
		session.beginTransaction();
		session.delete(objeto);
		session.getTransaction().commit();
		
	}
	@Override
	public List<PS> getAll() {
		List<PS> lista = (List) session.createQuery("from PS").list();
		return lista;
	}
}
