package com.smartps.dao;

import java.util.List;

import com.smartps.model.Area;

public interface IArea extends IGenericDAO<Area> {
	public List<Area> getAll();
}
