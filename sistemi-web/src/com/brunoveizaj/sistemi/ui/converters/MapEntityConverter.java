package com.brunoveizaj.sistemi.ui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.brunoveizaj.sistemi.ui.models.MapEntity;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;

@SuppressWarnings("rawtypes")
@FacesConverter("mapEntityConverter")
public class MapEntityConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		
		if(StringUtil.isValid(value))
		{
			MapEntity m = new MapEntity();
			m.setId(value);
			return m;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null)
		{
			return ((MapEntity)value).getId();
		}
		
		return null;
	} 
	
	
	

}
