package com.brunoveizaj.sistemi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunoveizaj.sistemi.models.map.BuildingMap;
import com.brunoveizaj.sistemi.models.map.PatronagePoint;
import com.brunoveizaj.sistemi.models.map.PersonPoint;
import com.brunoveizaj.sistemi.models.map.PoiPoint;
import com.brunoveizaj.sistemi.models.map.QvMap;
import com.brunoveizaj.sistemi.models.map.UnitMap;
import com.brunoveizaj.sistemi.service.MapService;
import com.brunoveizaj.sistemi.service.TokenService;


@RestController
@RequestMapping("/api/map")
public class MapAPI {

	
	@Autowired MapService mapService;
	@Autowired TokenService tokenService;
	
	@RequestMapping(value="/getPersonPointByNid/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPersonPointByNid(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonPoint> list = mapService.getPersonPointByNid(nid, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPatronagesPointByArea", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPatronagesPointByArea(@RequestHeader(value="Authorization") String token, 
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="unitId", required=false) Integer unitId,
			@RequestParam(name="patronageTypeId", required=false) Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PatronagePoint> list = mapService.getPatronagesPointByArea(qvId, unitId, patronageTypeId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getInPatronagePointsByPatronageNid/{patronageNid}/{patronageTypeId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getInPatronagePointsByPatronageNid(@RequestHeader(value="Authorization") String token, @PathVariable Integer patronageNid, @PathVariable Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonPoint> list = mapService.getInPatronagePointsByPatronageNid(patronageNid, patronageTypeId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getInPatronagePointsByArea", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getInPatronagePointsByArea(@RequestHeader(value="Authorization") String token, 
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="unitId", required=false) Integer unitId,
			@RequestParam(name="patronageTypeId", required=false) Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PatronagePoint> list = mapService.getPatronagesPointByArea(qvId, unitId, patronageTypeId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPoisPointByArea", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPoisPointByArea(@RequestHeader(value="Authorization") String token,
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="unitId", required=false) Integer unitId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PoiPoint> list = mapService.getPoisPointByArea(qvId, unitId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getBuildingsByArea", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getBuildingsByArea(@RequestHeader(value="Authorization") String token,
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="unitId", required=false) Integer unitId)
	{
		String uname = tokenService.getUsername(token);
		
		List<BuildingMap> list = mapService.getBuildingsByArea(qvId, unitId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getQvById/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getQvById(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		QvMap m = mapService.getQvById(qvId, uname);	
		
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getQvsByUnitId/{unitId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getQvsByUnitId(@RequestHeader(value="Authorization") String token, @PathVariable Integer unitId)
	{
		String uname = tokenService.getUsername(token);
		
		List<QvMap> list = mapService.getQvsByUnitId(unitId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getUnitById/{unitId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getUnitById(@RequestHeader(value="Authorization") String token, @PathVariable Integer unitId)
	{
		String uname = tokenService.getUsername(token);
		
		UnitMap m = mapService.getUnitById(unitId, uname);		
		
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFirstTimeVoterPointsByArea", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getFirstTimeVoterPointsByArea(@RequestHeader(value="Authorization") String token,
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="unitId", required=false) Integer unitId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonPoint> list = mapService.getFirstTimeVoterPointsByArea(qvId, unitId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMemberPointsByArea", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getMemberPointsByArea(@RequestHeader(value="Authorization") String token, 
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="unitId", required=false) Integer unitId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonPoint> list = mapService.getMemberPointsByArea(qvId, unitId, uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
