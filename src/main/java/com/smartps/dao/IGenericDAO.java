package com.smartps.dao;

//import java.util.List;

public interface IGenericDAO<T> {

	void save(T objeto);

	void update(T objeto);

	void delete(T objeto);

//	List<T> retrieveAll();
	
}
