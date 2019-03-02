package com.brunoveizaj.sistemi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunoveizaj.sistemi.assemblers.Assembler;
import com.brunoveizaj.sistemi.dto.InstitutionDTO;
import com.brunoveizaj.sistemi.dto.MunicipalityDTO;
import com.brunoveizaj.sistemi.dto.PartyDTO;
import com.brunoveizaj.sistemi.dto.PatronageTypeDTO;
import com.brunoveizaj.sistemi.dto.PoiTypeDTO;
import com.brunoveizaj.sistemi.dto.QvDTO;
import com.brunoveizaj.sistemi.dto.RegionDTO;
import com.brunoveizaj.sistemi.dto.RoleDTO;
import com.brunoveizaj.sistemi.dto.UnitDTO;
import com.brunoveizaj.sistemi.entities.Institution;
import com.brunoveizaj.sistemi.entities.Municipality;
import com.brunoveizaj.sistemi.entities.Party;
import com.brunoveizaj.sistemi.entities.PatronageType;
import com.brunoveizaj.sistemi.entities.PoiType;
import com.brunoveizaj.sistemi.entities.Qv;
import com.brunoveizaj.sistemi.entities.Region;
import com.brunoveizaj.sistemi.entities.Role;
import com.brunoveizaj.sistemi.entities.Unit;
import com.brunoveizaj.sistemi.models.MonthYear;
import com.brunoveizaj.sistemi.service.HelperService;

@RestController
@RequestMapping("/api/helper")
public class HelperAPI {

	
	@Autowired HelperService helperService;
	
	
	@RequestMapping(value="/list/party", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listParty()
	{
				
		List<PartyDTO> list = new Assembler().partyListToDto(helperService.listParty());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/party/{code}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findParty(@PathVariable String code)
	{
				
		PartyDTO p = new Assembler().toDto(helperService.findById(Party.class,code));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/poiType", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listPoiType()
	{
				
		List<PoiTypeDTO> list = new Assembler().poiTypeListToDto(helperService.listPoiType());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/poiType/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findPoiType(@PathVariable Integer id)
	{
				
		PoiTypeDTO p = new Assembler().toDto(helperService.findById(PoiType.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/patronageType", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listPatronageType()
	{
				
		List<PatronageTypeDTO> list = new Assembler().patronageTypeListToDto(helperService.listPatronageType());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/patronageType/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findPatronageType(@PathVariable Integer id)
	{
				
		PatronageTypeDTO p = new Assembler().toDto(helperService.findById(PatronageType.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/role", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listRole()
	{
				
		List<RoleDTO> list = new Assembler().roleListToDto(helperService.listRole());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/role/{code}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findRole(@PathVariable String code)
	{
				
		RoleDTO p = new Assembler().toDto(helperService.findById(Role.class,code));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/list/institution", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listInstitution()
	{
				
		List<InstitutionDTO> list = new Assembler().institutionListToDto(helperService.listInstitution());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/institution/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findInstitution(@PathVariable Integer id)
	{
				
		InstitutionDTO p = new Assembler().toDto(helperService.findById(Institution.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/list/region", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listRegion()
	{
				
		List<RegionDTO> list = new Assembler().regionListToDto(helperService.listRegion());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/region/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findRegion(@PathVariable Integer id)
	{
				
		RegionDTO p = new Assembler().toDto(helperService.findById(Region.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/municipality", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listMunicipality(@RequestParam(name="regionId", required=false) Integer regionId, @RequestParam(name="name", required=false) String name)
	{
				
		List<MunicipalityDTO> list = new Assembler().municipalityListToDto(helperService.listMunicipality(regionId, name));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/municipality/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findMunicipality(@PathVariable Integer id)
	{
				
		MunicipalityDTO p = new Assembler().toDto(helperService.findById(Municipality.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/unit", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listUnit(@RequestParam(name="regionId", required=false) Integer regionId,
									  @RequestParam(name="municipalityId", required=false) Integer municipalityId,
									  @RequestParam(name="name", required=false) String name)
	{
				
		List<UnitDTO> list = new Assembler().unitListToDto(helperService.listUnit(regionId, municipalityId, name));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/unit/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findUnit(@PathVariable Integer id)
	{
				
		UnitDTO p = new Assembler().toDto(helperService.findById(Unit.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/qv", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listQv(@RequestParam(name="regionId", required=false) Integer regionId,
									  @RequestParam(name="municipalityId", required=false) Integer municipalityId,
									  @RequestParam(name="unitId", required=false) Integer unitId,
									  @RequestParam(name="code", required=false) String code)
	{
				
		List<QvDTO> list = new Assembler().qvListToDto(helperService.listQv(regionId, municipalityId, unitId, code));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/find/qv/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findQv(@PathVariable Integer id)
	{
				
		QvDTO p = new Assembler().toDto(helperService.findById(Qv.class,id));
		
		if(p == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/payrollMonthYears", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listpayrollMonthYears()
	{
				
		List<MonthYear> list = helperService.getPayrollMonthYears();
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	
}
