package com.smartps.dao;

import com.smartps.model.Area;

public class AreaDao extends Dao<Area>{
	private static AreaDao instancia=null;

	public AreaDao(){
		super(Area.class);
	};
	
	public static AreaDao getInstance(){
		if(instancia ==null){
			instancia = new AreaDao();
		}
		return instancia;
	}
	
	
}
