package com.brunoveizaj.sistemi.ui.beans.application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.brunoveizaj.sistemi.ui.utils.PhotoUtil;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;




@ManagedBean
@ApplicationScoped
public class PhotoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	public StreamedContent getPhoto() throws IOException
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
		{
			return new DefaultStreamedContent();
		}
		
		byte[] imgByte = Base64.getMimeDecoder().decode(PhotoUtil.NO_IMAGE);
		
		try {
						
			String eventId = context.getExternalContext().getRequestParameterMap().get("event_id");
			if(!StringUtil.isValid(eventId))
			{
				return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
			}
			
			//BiometricDTO b = new BiometricService().findBiometric(Integer.valueOf(eventId));
			/*
			if(b != null )
			{
				 String base64 = null;//b.getPhoto();
				 if(StringUtil.isValid(base64))
				 {
				   imgByte = Base64.getMimeDecoder().decode(base64);
				 }
			}
			*/
			return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
			
		}catch(NullPointerException e)
		{
			return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
		}
		
		
		
	}

}
