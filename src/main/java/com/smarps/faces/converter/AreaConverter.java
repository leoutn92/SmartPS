package com.smarps.faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.smartps.dao.AreaDAO;
import com.smartps.model.Area;

@FacesConverter("areaConverter")
public class AreaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		System.out.println("nro de la lista "+value);
		if (value !=null && value.trim().length()>0){
			try{
				Area area = new AreaDAO().buscarArea(Integer.parseInt(value));
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
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
