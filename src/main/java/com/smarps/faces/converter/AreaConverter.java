package com.smarps.faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.smartps.dao.AreaDao;
import com.smartps.model.Area;

/**
 * @author Lucas 
 * Utilizar esta clase es angau, la "manera correcta" de obtener los datos de las oneMenuList, etc, casteados ya a la clase que uno necesia.
 * pero no pude hacerlo andar
 *
 */


@FacesConverter("areaConverter")
public class AreaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		System.out.println("valor del atributo pasado al converter "+value);
		if (value !=null && value.trim().length()>0){
			try{
				Area area = new AreaDao().buscarArea(Integer.parseInt(value));
				System.out.println(area.getDescripsion());
				return area;				
			} catch (NumberFormatException e){
				throw new ConverterException (new FacesMessage (FacesMessage.SEVERITY_ERROR, "Conversion Error","se rompio todo"));				
			}
		}
		else {
		return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
		if (obj!=null){
			return obj.toString();
		}
		return null;
	}

}
