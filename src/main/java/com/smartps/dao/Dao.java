package com.smartps.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smartps.util.HibernateUtil;

public class Dao<T>{
	
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	private Class<T> clazz;

    protected Dao(Class<T> clazz) {
        this.clazz = clazz;
    }
    	
	public void save(T t){
		this.getSession();
		Transaction tx =session.beginTransaction();
		try{
			session.save(t);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
		}
	}
	
	public T getById( int id){
		
		this.getSession();
		Transaction tx =session.beginTransaction();
		T t = null;
		try{
			t = (T) session.get( clazz , id);
			tx.commit();
		} catch (HibernateException e){
			tx.rollback();
		}
		return t;
	}
	
	public List<T> getAll(){
		this.getSession();
		Transaction tx =session.beginTransaction();
		List<T> lista = null;
		try{
			lista = session.createQuery("from " + clazz.getName()).getResultList();
			tx.commit();
		} catch (HibernateException e){
			tx.rollback();
		}
		return lista;
		
	}
	
	public void update(T t){
		this.getSession();
		Transaction tx =session.beginTransaction();
		try{
			session.update(t);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		}
	}
	
	
	public void saveOrUpdate(T t){
		this.getSession();
		Transaction tx =session.beginTransaction();
		try{
			session.saveOrUpdate(t);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		}
	}
	
	public void delete (T t){
		this.getSession();
		Transaction tx =session.beginTransaction();
		try {
			session.delete(t);
			tx.commit();
		} catch (HibernateException e){
			tx.rollback();
		}
	}
	
	protected void getSession(){
		if (!session.isOpen()){
			session= HibernateUtil.getSessionFactory().openSession();
		}
	}
	

		
}
