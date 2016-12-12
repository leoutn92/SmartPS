package com.smartps.dao;

import java.util.List;

public interface IDao<E> {
	public List<E> getAll();
	public E getById(int i);
	public void update(E e);
	public void save (E e);
	public void delete (E e);
	public void saveOrUpdate( E e);
}
