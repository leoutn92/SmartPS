package com.smartps.dao;

import java.util.List;

import org.hibernate.Session;

import com.smartps.model.PlanDeTrabajo;
import com.smartps.model.TipoActividad;

public class TipoActividadDao extends Dao<TipoActividad>{	
	private static TipoActividadDao instancia;
	
	public TipoActividadDao(){
		super(TipoActividad.class);
	}
	
	public static TipoActividadDao getInstance(){
		if (instancia == null){
			instancia= new TipoActividadDao();
		}
		return instancia;
	}
	

	
	//TODO reemplazar este metodo
	public TipoActividad findById(int id){
		return this.getById(id);
	}
	
}