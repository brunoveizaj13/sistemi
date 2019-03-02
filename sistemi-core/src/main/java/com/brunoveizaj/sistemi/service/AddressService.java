package com.brunoveizaj.sistemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.AddressDAO;
import com.brunoveizaj.sistemi.entities.Address;

@Service
public class AddressService {
	
	
	@Autowired AddressDAO addressDAO;
	
	
	public List<Address> getPersonAddresses(String nid, String uname)
	{
		return addressDAO.getAddressByNid(nid);
	}
	
	public List<Address> getBuildingAddresses(Integer buildingId, String uname)
	{
		return addressDAO.getAddressByBuildingId(buildingId);
	}
	
	
	
	

}
