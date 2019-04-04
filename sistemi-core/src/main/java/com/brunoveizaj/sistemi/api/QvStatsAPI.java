package com.brunoveizaj.sistemi.api;

import java.util.Date;
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

import com.brunoveizaj.sistemi.assemblers.Assembler;
import com.brunoveizaj.sistemi.dto.AlbaniaStatisticDTO;
import com.brunoveizaj.sistemi.dto.MunicipalityStatisticDTO;
import com.brunoveizaj.sistemi.dto.PersonDTO;
import com.brunoveizaj.sistemi.dto.QvStatisticDTO;
import com.brunoveizaj.sistemi.dto.RegionStatisticDTO;
import com.brunoveizaj.sistemi.dto.UnitStatisticDTO;
import com.brunoveizaj.sistemi.models.KeyValue;
import com.brunoveizaj.sistemi.service.QvStatsService;
import com.brunoveizaj.sistemi.service.TokenService;
import com.brunoveizaj.sistemi.utils.DateUtil;

@RestController
@RequestMapping("/api/statistic/qv")
public class QvStatsAPI {

	
	@Autowired TokenService tokenService;
	@Autowired QvStatsService qvStatsService;
	
	@RequestMapping(value="/persons/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> persons(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.persons(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/personsNotInPatronage/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> personsNotInPatronage(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.personsNotInPatronage(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/personsInPatronage/{qvId}/{patronageTypeId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> personsInPatronage(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId, @PathVariable Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.personsInPatronage(qvId, patronageTypeId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/patronages/{qvId}/{patronageTypeId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> patronages(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId, @PathVariable Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.patronages(qvId, patronageTypeId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/firstTimeVoters/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> firstTimeVoters(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.firstTimeVoters(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/notVotingLastElections/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> notVotingLastElections(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.notVotingLastElections(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getQvStatistic/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getQvStatistic(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		tokenService.getUsername(token);
		
		QvStatisticDTO s = new Assembler().toDto(qvStatsService.getQvStatistic(qvId));
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getUnitStatistic/{unitId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getUnitStatistic(@RequestHeader(value="Authorization") String token, @PathVariable Integer unitId)
	{
		tokenService.getUsername(token);
		
		UnitStatisticDTO s = new Assembler().toDto(qvStatsService.getUnitStatistic(unitId));
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMunicipalityStatistic/{municipalityId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getMunicipalityStatistic(@RequestHeader(value="Authorization") String token, @PathVariable Integer municipalityId)
	{
		tokenService.getUsername(token);
		
		MunicipalityStatisticDTO s = new Assembler().toDto(qvStatsService.getMunicipalityStatistic(municipalityId));
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRegionStatistic/{regionId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getRegionStatistic(@RequestHeader(value="Authorization") String token, @PathVariable Integer regionId)
	{
		tokenService.getUsername(token);
		
		RegionStatisticDTO s = new Assembler().toDto(qvStatsService.getRegionStatistic(regionId));
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAlbaniaStatistic", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getQvStatistic(@RequestHeader(value="Authorization") String token)
	{
		tokenService.getUsername(token);
		
		AlbaniaStatisticDTO s = new Assembler().toDto(qvStatsService.getAlbaniaStatistic());
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getQvStatisticsByUnit/{unitId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getQvStatisticsByUnit(@RequestHeader(value="Authorization") String token, @PathVariable Integer unitId)
	{
		tokenService.getUsername(token);
		
		List<QvStatisticDTO> list = new Assembler().qvStatsListToDto(qvStatsService.getQvStatisticsByUnit(unitId));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getUnitStatisticsByMunicipality/{regionId}/{municipalityId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getUnitStatisticsByMunicipality(@RequestHeader(value="Authorization") String token,
			@PathVariable Integer regionId, @PathVariable Integer municipalityId)
	{
		tokenService.getUsername(token);
		
		if(regionId == 0) regionId = null;
		if(municipalityId == 0) municipalityId = null;
		
		List<UnitStatisticDTO> list = new Assembler().unitStatsListToDto(qvStatsService.getUnitStatisticsByMunicipality(regionId, municipalityId));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getMunicipalityStatisticsByRegion/{regionId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getMunicipalityStatisticsByRegion(@RequestHeader(value="Authorization") String token, @PathVariable Integer regionId)
	{
		tokenService.getUsername(token);
		
		List<MunicipalityStatisticDTO> list = new Assembler().municipalityStatsListToDto(qvStatsService.getMunicipalityStatisticsByRegion(regionId));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getRegionStatistics", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getRegionStatistics(@RequestHeader(value="Authorization") String token)
	{
		tokenService.getUsername(token);
		
		List<RegionStatisticDTO> list = new Assembler().regionStatsListToDto(qvStatsService.getRegionStatistics());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getInstitutionPatronages/{regionId}/{municipalityId}/{unitId}/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getInstitutionPatronages(@RequestHeader(value="Authorization") String token,
			@PathVariable Integer regionId, @PathVariable Integer municipalityId, @PathVariable Integer unitId, @PathVariable Integer qvId)
	{
		tokenService.getUsername(token);
		
		if(regionId == 0) regionId = null;
		if(municipalityId == 0) municipalityId = null;
		if(unitId == 0) unitId = null;
		if(qvId == 0) qvId = null;
		
		
		List<KeyValue> list = qvStatsService.getInstitutionPatronages(regionId, municipalityId, unitId, qvId);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getInstitutionInPatronages/{regionId}/{municipalityId}/{unitId}/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getInstitutionInPatronages(@RequestHeader(value="Authorization") String token,
			@PathVariable Integer regionId, @PathVariable Integer municipalityId, @PathVariable Integer unitId, @PathVariable Integer qvId)
	{
		tokenService.getUsername(token);
		
		if(regionId == 0) regionId = null;
		if(municipalityId == 0) municipalityId = null;
		if(unitId == 0) unitId = null;
		if(qvId == 0) qvId = null;
		
		List<KeyValue> list = qvStatsService.getInstitutionInPatronages(regionId, municipalityId, unitId, qvId);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	//****************
	
	
	@RequestMapping(value="/getPatronagesByDate/{type}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPatronagesByDate(@RequestHeader(value="Authorization") String token,
			@PathVariable Integer type,
			@RequestParam(name="regionId", required=false) Integer regionId,
			@RequestParam(name="municipalityId", required=false) Integer municipalityId,
			@RequestParam(name="unitId", required=false) Integer unitId,
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="from", required=false) String from,
			@RequestParam(name="to", required=false) String to)
	{
		tokenService.getUsername(token);
		
		Date fromDate = DateUtil.formatStringToDate(from);
		Date toDate = DateUtil.formatStringToDate(to);
		
		List<KeyValue> list = qvStatsService.getPatronagesByDate(fromDate, toDate, type, regionId, municipalityId, unitId, qvId);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getInPatronagesByDate/{type}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getInPatronagesByDate(@RequestHeader(value="Authorization") String token,
			@PathVariable Integer type,
			@RequestParam(name="regionId", required=false) Integer regionId,
			@RequestParam(name="municipalityId", required=false) Integer municipalityId,
			@RequestParam(name="unitId", required=false) Integer unitId,
			@RequestParam(name="qvId", required=false) Integer qvId,
			@RequestParam(name="from", required=false) String from,
			@RequestParam(name="to", required=false) String to)
	{
		tokenService.getUsername(token);
		
		Date fromDate = DateUtil.formatStringToDate(from);
		Date toDate = DateUtil.formatStringToDate(to);
		
		List<KeyValue> list = qvStatsService.getInPatronagesByDate(fromDate, toDate, type, regionId, municipalityId, unitId, qvId);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	
	
	
}
