package com.smartps.util.test;

import java.util.List;

import com.smartps.dao.AreaDAO;
import com.smartps.model.Area;
import com.smartps.model.PS;

public class TestAreaDAO {

	public static void main(String[] args) {
		Area area= new AreaDAO().buscarArea(2);
		System.out.println(area.getNombre().toString());
		
		String string1 = "1";
		String string2 = "2";
		int result = Integer.parseInt(string1)+Integer.parseInt(string2);
		System.out.println(result);
		PS ps=new PS();
		ps.getArea();

	}

}
