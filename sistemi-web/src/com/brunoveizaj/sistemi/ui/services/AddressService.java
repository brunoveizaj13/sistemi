package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.AddressClient;
import com.brunoveizaj.sistemi.ui.models.AddressDTO;

public class AddressService {
	
	
	
	public List<AddressDTO> getPersonAddresses(String nid)
	{
		return new AddressClient().getPersonAddresses(nid);
	}
	
	public List<AddressDTO> getBuildingAddresses(Integer buildingId)
	{
		return new AddressClient().getBuildingAddresses(buildingId);
	}

}
