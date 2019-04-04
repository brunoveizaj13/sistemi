package com.brunoveizaj.sistemi.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.dto.AddressDTO;
import com.brunoveizaj.sistemi.dto.AlbaniaStatisticDTO;
import com.brunoveizaj.sistemi.dto.BuildingDTO;
import com.brunoveizaj.sistemi.dto.DepartmentDTO;
import com.brunoveizaj.sistemi.dto.DepartmentFunctionDTO;
import com.brunoveizaj.sistemi.dto.DepartmentPositionDTO;
import com.brunoveizaj.sistemi.dto.EmployeeDTO;
import com.brunoveizaj.sistemi.dto.EmployeePeriodDTO;
import com.brunoveizaj.sistemi.dto.InstitutionDTO;
import com.brunoveizaj.sistemi.dto.MunicipalityDTO;
import com.brunoveizaj.sistemi.dto.MunicipalityStatisticDTO;
import com.brunoveizaj.sistemi.dto.PartyDTO;
import com.brunoveizaj.sistemi.dto.PartyStructureDTO;
import com.brunoveizaj.sistemi.dto.PatronageDTO;
import com.brunoveizaj.sistemi.dto.PatronagePersonDTO;
import com.brunoveizaj.sistemi.dto.PatronageTypeDTO;
import com.brunoveizaj.sistemi.dto.PersonDTO;
import com.brunoveizaj.sistemi.dto.PoiTypeDTO;
import com.brunoveizaj.sistemi.dto.QvDTO;
import com.brunoveizaj.sistemi.dto.QvStatisticDTO;
import com.brunoveizaj.sistemi.dto.RegionDTO;
import com.brunoveizaj.sistemi.dto.RegionStatisticDTO;
import com.brunoveizaj.sistemi.dto.RoleDTO;
import com.brunoveizaj.sistemi.dto.SubjectDTO;
import com.brunoveizaj.sistemi.dto.UnitDTO;
import com.brunoveizaj.sistemi.dto.UnitStatisticDTO;
import com.brunoveizaj.sistemi.dto.UserDTO;
import com.brunoveizaj.sistemi.entities.Address;
import com.brunoveizaj.sistemi.entities.AlbaniaStatistic;
import com.brunoveizaj.sistemi.entities.Building;
import com.brunoveizaj.sistemi.entities.Department;
import com.brunoveizaj.sistemi.entities.DepartmentFunction;
import com.brunoveizaj.sistemi.entities.DepartmentPosition;
import com.brunoveizaj.sistemi.entities.Employee;
import com.brunoveizaj.sistemi.entities.EmploymentPeriod;
import com.brunoveizaj.sistemi.entities.Institution;
import com.brunoveizaj.sistemi.entities.Municipality;
import com.brunoveizaj.sistemi.entities.MunicipalityStatistic;
import com.brunoveizaj.sistemi.entities.Party;
import com.brunoveizaj.sistemi.entities.PartyStructure;
import com.brunoveizaj.sistemi.entities.Patronage;
import com.brunoveizaj.sistemi.entities.PatronagePerson;
import com.brunoveizaj.sistemi.entities.PatronageType;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.entities.PoiType;
import com.brunoveizaj.sistemi.entities.Qv;
import com.brunoveizaj.sistemi.entities.QvStatistic;
import com.brunoveizaj.sistemi.entities.Region;
import com.brunoveizaj.sistemi.entities.RegionStatistic;
import com.brunoveizaj.sistemi.entities.Role;
import com.brunoveizaj.sistemi.entities.Subject;
import com.brunoveizaj.sistemi.entities.Unit;
import com.brunoveizaj.sistemi.entities.UnitStatistic;
import com.brunoveizaj.sistemi.entities.User;
import com.brunoveizaj.sistemi.utils.DateUtil;

public class Assembler {
	
	
	
	public PersonDTO toDto(Person e)
	{
		if(e == null) return null;
		
		PersonDTO dto = new PersonDTO();
		
		dto.setDapStatus(e.getDetails().getDapStatus() != null && e.getDetails().getDapStatus().intValue() == IStatus.ACTIVE);
		dto.setDob(DateUtil.formatReverseToNormalDate(e.getDob()));
		dto.setEconomicHelpStatus(e.getDetails().getEconomicHelpStatus() != null && e.getDetails().getEconomicHelpStatus().intValue() == IStatus.ACTIVE);
		dto.setEmail(e.getDetails().getEmail());
		dto.setFamilyId(e.getFamilyId()==null?0:e.getFamilyId().longValue());
		dto.setFamilyRelation(e.getFamilyRelation());
		dto.setFatherName(e.getFatherName());
		dto.setFirstTimeVoterStatus(e.getDetails().getFirstTimeVoterStatus() != null && e.getDetails().getFirstTimeVoterStatus().intValue() == IStatus.ACTIVE);
		dto.setFraction(e.getFraction());
		dto.setGender(e.getGender());
		dto.setMaidenName(e.getMaidenName());
		dto.setMemberStatus(e.getDetails().getMemberStatus() != null && e.getDetails().getMemberStatus().intValue() == IStatus.ACTIVE);
		dto.setMotherName(e.getMotherName());
		dto.setName(e.getName());
		dto.setNid(e.getNid());
		dto.setPatronageInstitutionStatus(e.getDetails().getPatronageInstitutionStatus() != null && e.getDetails().getPatronageInstitutionStatus().intValue() == IStatus.ACTIVE);
		dto.setPatronageStatus(e.getDetails().getPatronageStatus() != null && e.getDetails().getPatronageStatus().intValue() == IStatus.ACTIVE);
		dto.setPhone(e.getDetails().getPhone());
		dto.setPoiStatus(e.getDetails().getPoiStatus() != null && e.getDetails().getPoiStatus().intValue() == IStatus.ACTIVE);
		dto.setQv(toDto(e.getQv()));
		dto.setQvAddress(e.getQvAddress());
		dto.setSocialHouseStatus(e.getDetails().getSocialHouseStatus() != null && e.getDetails().getSocialHouseStatus().intValue() == IStatus.ACTIVE);
		dto.setSubjectPartyCode(e.getSubjectPartyCode());
		dto.setSurname(e.getSurname());
		dto.setVotingNo(e.getVotingNo()==null?0:e.getVotingNo().intValue());
		dto.setVoting2017Status(e.getDetails().getVoting2017Status() != null && e.getDetails().getVoting2017Status().intValue() == IStatus.ACTIVE);
		
		
		
		return dto;
	}
	
	public List<PersonDTO> personListToDto(List<Person> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PersonDTO> list = new ArrayList<>();
		
		for(Person i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public QvDTO toDto(Qv e) {

		if(e == null) return null;
		
		QvDTO dto = new QvDTO();
		
		dto.setCode(e.getCode());
		dto.setId(e.getId().intValue());
		dto.setUnit(toDto(e.getUnit()));
		
		return dto;
		
	}
	
	public List<QvDTO> qvListToDto(List<Qv> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<QvDTO> list = new ArrayList<>();
		
		for(Qv i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}

	public UnitDTO toDto(Unit e) {

		if(e == null) return null;
		
		UnitDTO dto = new UnitDTO();
		
		dto.setId(e.getId().intValue());
		dto.setName(e.getName());
		dto.setMunicipality(toDto(e.getMunicipality()));
		
		return dto;
		
	}

	public List<UnitDTO> unitListToDto(List<Unit> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<UnitDTO> list = new ArrayList<>();
		
		for(Unit i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public MunicipalityDTO toDto(Municipality e) {
		
		if(e == null) return null;
		
		MunicipalityDTO dto = new MunicipalityDTO();
		
		dto.setId(e.getId().intValue());
		dto.setName(e.getName());
		dto.setRegion(toDto(e.getRegion()));
		
		return dto;
		
	}
	
	public List<MunicipalityDTO> municipalityListToDto(List<Municipality> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<MunicipalityDTO> list = new ArrayList<>();
		
		for(Municipality i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public RegionDTO toDto(Region e) {
		
		if(e == null) return null;
		
		RegionDTO dto = new RegionDTO();
		
		dto.setId(e.getId().intValue());
		dto.setName(e.getName());
		
		return dto;
		
	}
	
	public List<RegionDTO> regionListToDto(List<Region> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<RegionDTO> list = new ArrayList<>();
		
		for(Region i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
    public InstitutionDTO toDto(Institution e) {
		
		if(e == null) return null;
		
		InstitutionDTO dto = new InstitutionDTO();
		
		dto.setId(e.getId().intValue());
		dto.setName(e.getName());
		dto.setActive(e.getStatus() != null && e.getStatus().intValue() == IStatus.ACTIVE);
		dto.setShortName(e.getShortName());
		dto.setRank(e.getRank() == null?0:e.getRank().intValue());
		
		return dto;
		
	}

	public List<InstitutionDTO> institutionListToDto(List<Institution> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<InstitutionDTO> list = new ArrayList<>();
		
		for(Institution i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public PatronageTypeDTO toDto(PatronageType e) {
		
		if(e == null) return null;
		
		PatronageTypeDTO dto = new PatronageTypeDTO();
		
		dto.setId(e.getId().intValue());
		dto.setTag(e.getTag());
		
		return dto;
		
	}
	
	public List<PatronageTypeDTO> patronageTypeListToDto(List<PatronageType> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PatronageTypeDTO> list = new ArrayList<>();
		
		for(PatronageType i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public PoiTypeDTO toDto(PoiType e) {
		
		if(e == null) return null;
		
		PoiTypeDTO dto = new PoiTypeDTO();
		
		dto.setId(e.getId().intValue());
		dto.setTag(e.getTag());
		dto.setActive(e.getStatus() != null && e.getStatus().intValue() == IStatus.ACTIVE);
		dto.setRank(e.getRank() == null?0:e.getRank().intValue());
		
		return dto;
		
	}		
	
	public List<PoiTypeDTO> poiTypeListToDto(List<PoiType> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PoiTypeDTO> list = new ArrayList<>();
		
		for(PoiType i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
    public PatronageDTO toDto(Patronage e) {
		
		if(e == null) return null;
		
		PatronageDTO dto = new PatronageDTO();
		
		dto.setId(e.getId().intValue());
		dto.setPatronageType(toDto(e.getPatronageType()));
		dto.setActive(e.getStatus() != null && e.getStatus().intValue() == IStatus.ACTIVE);
		dto.setCreateTime(e.getCreateTime());
		dto.setCreateUser(e.getCreateUser());
		dto.setInstitution(toDto(e.getInstitution()));
		dto.setPerson(toDto(e.getPerson()));
		dto.setUpdateTime(e.getUpdateTime());
		dto.setUpdateUser(e.getUpdateUser());
		
		return dto;
		
	}
	
    public List<PatronageDTO> patronageListToDto(List<Patronage> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PatronageDTO> list = new ArrayList<>();
		
		for(Patronage i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
    public PatronagePersonDTO toDto(PatronagePerson e) {
		
		if(e == null) return null;
		
		PatronagePersonDTO dto = new PatronagePersonDTO();
		
		dto.setId(e.getId().intValue());
		dto.setActive(e.getStatus() != null && e.getStatus().intValue() == IStatus.ACTIVE);
		dto.setCreateTime(e.getCreateTime());
		dto.setCreateUser(e.getCreateUser());
		dto.setPerson(toDto(e.getPerson()));
		dto.setUpdateTime(e.getUpdateTime());
		dto.setUpdateUser(e.getUpdateUser());
		dto.setPatronage(toDto(e.getPatronage()));
		
		return dto;
		
	}
    
    
    public List<PatronagePersonDTO> patronagePersonListToDto(List<PatronagePerson> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PatronagePersonDTO> list = new ArrayList<>();
		
		for(PatronagePerson i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
    public BuildingDTO toDto(Building e) {
		
		if(e == null) return null;
		
		BuildingDTO dto = new BuildingDTO();
		
		dto.setBuildingCode(e.getBuildingCode());
		dto.setBuildingNo(e.getBuildingNo());
		dto.setHasData(e.getHasData() != null && e.getHasData().intValue() == IStatus.ACTIVE);
		dto.setId(e.getId());
		dto.setQv(toDto(e.getQv()));
		
		return dto;
		
	}		
    
    public List<BuildingDTO> buildingListToDto(List<Building> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<BuildingDTO> list = new ArrayList<>();
		
		for(Building i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
    
    
public AddressDTO toDto(Address e) {
		
		if(e == null) return null;
		
		AddressDTO dto = new AddressDTO();
		
		dto.setBuildingCode(e.getBuildingCode());
		dto.setBuildingNo(e.getBuildingNo());
		dto.setId(e.getId());
		dto.setAddressId(e.getAddressId());
		dto.setAdmUnit(e.getAdmUnit());
		dto.setBuildingEntrance(e.getBuildingEntrance());
		dto.setBuildingId(e.getBuildingId());
		dto.setCity(e.getCity());
		dto.setEntranceId(e.getEntranceId());
		dto.setEntranceNo(e.getEntranceNo());
		dto.setMunicipality(e.getMunicipality());
		dto.setName(e.getName());
		dto.setNid(e.getNid());
		dto.setStreet(e.getStreet());
		dto.setSubjectType(e.getSubjectType());
		
		
		return dto;
		
	}		
    
    public List<AddressDTO> addressListToDto(List<Address> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<AddressDTO> list = new ArrayList<>();
		
		for(Address i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
    
    
    
    
	
	public SubjectDTO toDto(Subject e)
	{
		if(e == null) return null;
		
		SubjectDTO dto = new SubjectDTO();
		
		dto.setActivity(e.getActivity());
		dto.setAddress(e.getAddress());
		dto.setAdministrator(e.getAdministrator());
		dto.setAgency(e.getAgency());
		dto.setBussinesType(e.getBussinesType());
		dto.setCity(e.getCity());
		dto.setDrt(e.getDrt());
		dto.setEmail(e.getEmail());
		dto.setId(e.getId().intValue());
		dto.setLegalForm(e.getLegalForm());
		dto.setMunicipality(e.getMunicipality());
		dto.setName(e.getName());
		dto.setNipt(e.getNipt());
		dto.setOwnerState(e.getOwnerState());
		dto.setPhoneNo(e.getPhoneNo());
		dto.setRegion(e.getRegion());
		dto.setRegisteredDate(e.getRegisteredDate());
		dto.setSectorType(e.getSectorType());
		dto.setState(e.getState());
		dto.setStatus(e.getStatus());
		dto.setStatusDate(e.getStatusDate());
		dto.setStreetName(e.getStreetName());	
		
		return dto;
	}
	
	public List<SubjectDTO> subjectListToDto(List<Subject> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<SubjectDTO> list = new ArrayList<>();
		
		for(Subject i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public EmployeeDTO toDto(Employee e)
	{
		if(e == null) return null;
		
		EmployeeDTO dto = new EmployeeDTO();
		
		
		dto.setDob(e.getDob());
		dto.setGender(e.getGender());
		dto.setId(e.getId());
		dto.setInternationalNid(e.getInternationalNid());
		dto.setMonth(e.getMonth());
		dto.setName(e.getName());
		dto.setNipt(e.getNipt());
		dto.setProfession(e.getProfession());
		dto.setSalary(e.getSalary());
		dto.setSubject(e.getSubject());
		dto.setSurname(e.getSurname());
		dto.setYear(e.getYear());		
		
		return dto;
	}
	
	
	public List<EmployeeDTO> employeeListToDto(List<Employee> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<EmployeeDTO> list = new ArrayList<>();
		
		for(Employee i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public PartyDTO toDto(Party e)
	{
		if(e == null) return null;
		
		PartyDTO dto = new PartyDTO();
		
		dto.setCode(e.getCode());
		dto.setName(e.getName());
		dto.setRank(e.getRank().intValue());
		dto.setShortName(e.getShortName());
		dto.setColor(e.getColor());
		
		return dto;
	}
	
	public List<PartyDTO> partyListToDto(List<Party> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PartyDTO> list = new ArrayList<>();
		
		for(Party i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	
	public RoleDTO toDto(Role e)
	{
		if(e == null) return null;
		
		RoleDTO dto = new RoleDTO();
		
		dto.setCode(e.getCode());
		dto.setTag(e.getTag());
		
		
		return dto;
	}
	
	public List<RoleDTO> roleListToDto(List<Role> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<RoleDTO> list = new ArrayList<>();
		
		for(Role i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public UserDTO toDto(User e)
	{
		if(e == null) return null;
		
		UserDTO dto = new UserDTO();
		
		dto.setCreateTime(e.getCreateTime());
		dto.setId(e.getId().intValue());
		dto.setModifyTime(e.getModifyTime());
		dto.setRole(toDto(e.getRole()));
		dto.setActive(e != null && e.getStatus().intValue()==IStatus.ACTIVE);
		dto.setUsername(e.getUsername());
		dto.setSecret(e.getSecret());
		dto.setMunicipality(e.getMunicipality());
		dto.setInstitution(toDto(e.getInstitution()));
		dto.setCreateUserId(e.getCreateUserId() == null?0:e.getCreateUserId().intValue());
		dto.setModifyUserId(e.getModifyUserId() == null?0:e.getModifyUserId().intValue());
		
		
		return dto;
	}
	
	public List<UserDTO> userListToDto(List<User> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<UserDTO> list = new ArrayList<>();
		
		for(User i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public QvStatisticDTO toDto(QvStatistic e) {

		if(e == null) return null;
		
		QvStatisticDTO dto = new QvStatisticDTO();
		
		dto.setCode(e.getCode());
		dto.setId(e.getId().intValue());
		dto.setUnit(toDto(e.getUnit()));
		dto.setFirstTime(e.getFirstTime());
		dto.setInInstPatronages(e.getInInstPatronages());
		dto.setInstPatronages(e.getInstPatronages());
		dto.setInPatronages(e.getInPatronages());
		dto.setMembers(e.getMembers());
		dto.setNoVoting(e.getNoVoting());
		dto.setPatronages(e.getPatronages());
		dto.setPersons(e.getPersons());
		dto.setPois(e.getPois());
		dto.setVoters(e.getVoters());
		
		return dto;
		
	}
	
	public List<QvStatisticDTO> qvStatsListToDto(List<QvStatistic> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<QvStatisticDTO> list = new ArrayList<>();
		
		for(QvStatistic i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public UnitStatisticDTO toDto(UnitStatistic e) {

		if(e == null) return null;
		
		UnitStatisticDTO dto = new UnitStatisticDTO();
		
		dto.setUnit(e.getUnit());
		dto.setId(e.getId().intValue());
		dto.setQvs(e.getQvs());
		dto.setMunicipality(toDto(e.getMunicipality()));
		dto.setFirstTime(e.getFirstTime());
		dto.setInstPatronages(e.getInstPatronages());
		dto.setInInstPatronages(e.getInInstPatronages());
		dto.setInPatronages(e.getInPatronages());
		dto.setMembers(e.getMembers());
		dto.setNoVoting(e.getNoVoting());
		dto.setPatronages(e.getPatronages());
		dto.setPersons(e.getPersons());
		dto.setPois(e.getPois());
		dto.setVoters(e.getVoters());
		
		return dto;
		
	}
	
	public List<UnitStatisticDTO> unitStatsListToDto(List<UnitStatistic> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<UnitStatisticDTO> list = new ArrayList<>();
		
		for(UnitStatistic i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public MunicipalityStatisticDTO toDto(MunicipalityStatistic e) {

		if(e == null) return null;
		
		MunicipalityStatisticDTO dto = new MunicipalityStatisticDTO();
		
		dto.setUnits(e.getUnits());
		dto.setId(e.getId().intValue());
		dto.setRegion(toDto(e.getRegion()));
		dto.setMunicipality(e.getMunicipality());
		dto.setFirstTime(e.getFirstTime());
		dto.setInstPatronages(e.getInstPatronages());
		dto.setInInstPatronages(e.getInInstPatronages());
		dto.setInPatronages(e.getInPatronages());
		dto.setMembers(e.getMembers());
		dto.setNoVoting(e.getNoVoting());
		dto.setPatronages(e.getPatronages());
		dto.setPersons(e.getPersons());
		dto.setPois(e.getPois());
		dto.setVoters(e.getVoters());
		
		return dto;
		
	}
	
	public List<MunicipalityStatisticDTO> municipalityStatsListToDto(List<MunicipalityStatistic> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<MunicipalityStatisticDTO> list = new ArrayList<>();
		
		for(MunicipalityStatistic i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public RegionStatisticDTO toDto(RegionStatistic e) {

		if(e == null) return null;
		
		RegionStatisticDTO dto = new RegionStatisticDTO();
		
		dto.setMunicipalities(e.getMunicipalities());
		dto.setId(e.getId().intValue());
		dto.setRegion(e.getRegion());
		dto.setFirstTime(e.getFirstTime());
		dto.setInstPatronages(e.getInstPatronages());
		dto.setInInstPatronages(e.getInInstPatronages());
		dto.setInPatronages(e.getInPatronages());
		dto.setMembers(e.getMembers());
		dto.setNoVoting(e.getNoVoting());
		dto.setPatronages(e.getPatronages());
		dto.setPersons(e.getPersons());
		dto.setPois(e.getPois());
		dto.setVoters(e.getVoters());
		
		return dto;
		
	}
	
	public List<RegionStatisticDTO> regionStatsListToDto(List<RegionStatistic> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<RegionStatisticDTO> list = new ArrayList<>();
		
		for(RegionStatistic i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public AlbaniaStatisticDTO toDto(AlbaniaStatistic e) {

		if(e == null) return null;
		
		AlbaniaStatisticDTO dto = new AlbaniaStatisticDTO();
		
		dto.setRegions(e.getRegions());
		dto.setId(e.getId().intValue());
		dto.setName(e.getName());
		dto.setFirstTime(e.getFirstTime());
		dto.setInstPatronages(e.getInstPatronages());
		dto.setInInstPatronages(e.getInInstPatronages());
		dto.setInPatronages(e.getInPatronages());
		dto.setMembers(e.getMembers());
		dto.setNoVoting(e.getNoVoting());
		dto.setPatronages(e.getPatronages());
		dto.setPersons(e.getPersons());
		dto.setPois(e.getPois());
		dto.setVoters(e.getVoters());
		
		return dto;
		
	}
	
	
	public PartyStructureDTO toDto(PartyStructure e)
	{
		if(e == null) return null;
		
		PartyStructureDTO dto = new PartyStructureDTO();
		dto.setActive((e.getActive() != null) && (e.getActive() == IStatus.ACTIVE));
		dto.setEndDate(e.getEndDate());
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setStartDate(e.getStartDate());
		dto.setType(e.getType());
		
		return dto;
	}
	
	public List<PartyStructureDTO> partyStructureListToDto(List<PartyStructure> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<PartyStructureDTO> list = new ArrayList<>();
		
		for(PartyStructure i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public DepartmentFunctionDTO toDto(DepartmentFunction e)
	{
		if(e == null) return null;
		
		DepartmentFunctionDTO dto = new DepartmentFunctionDTO();
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setId(e.getId());
		dto.setName(e.getName());

		
		return dto;
	}
	
	public List<DepartmentFunctionDTO> departmentFunctionListToDto(List<DepartmentFunction> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<DepartmentFunctionDTO> list = new ArrayList<>();
		
		for(DepartmentFunction i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	public DepartmentDTO toDto(Department e)
	{
		if(e == null) return null;
		
		DepartmentDTO dto = new DepartmentDTO();
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setArea(e.getArea());
		dto.setFraction(e.getFraction());
		dto.setMunicipalityId(e.getMunicipalityId());
		dto.setQvId(e.getQvId());
		dto.setRegionId(e.getRegionId());
		dto.setSinglePosition((e.getSinglePosition() != null) && (e.getSinglePosition() == IStatus.ACTIVE));
		dto.setStructure(toDto(e.getStructure()));
		dto.setUnitId(e.getUnitId());
		dto.setExpanded((e.getExpanded() != null) && (e.getExpanded() == IStatus.ACTIVE));
		dto.setColor(e.getColor());
		
		if(e.getParent() != null)
		{
			DepartmentDTO d = new DepartmentDTO();
			d.setStatus((e.getParent().getStatus() != null) && (e.getParent().getStatus() == IStatus.ACTIVE));
			d.setId(e.getParent().getId());
			d.setName(e.getParent().getName());
			d.setArea(e.getParent().getArea());
			d.setFraction(e.getParent().getFraction());
			d.setMunicipalityId(e.getParent().getMunicipalityId());
			d.setQvId(e.getParent().getQvId());
			d.setRegionId(e.getParent().getRegionId());
			d.setSinglePosition((e.getParent().getSinglePosition() != null) && (e.getParent().getSinglePosition() == IStatus.ACTIVE));
			d.setStructure(toDto(e.getParent().getStructure()));
			d.setUnitId(e.getParent().getUnitId());
			d.setExpanded((e.getParent().getExpanded() != null) && (e.getParent().getExpanded() == IStatus.ACTIVE));
			d.setColor(e.getParent().getColor());
			d.setParent(d);
		}

		
		return dto;
	}
	
	public List<DepartmentDTO> departmentListToDto(List<Department> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<DepartmentDTO> list = new ArrayList<>();
		
		for(Department i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public DepartmentPositionDTO toDto(DepartmentPosition e)
	{
		if(e == null) return null;
		
		DepartmentPositionDTO dto = new DepartmentPositionDTO();
		dto.setStatus((e.getStatus() != null) && (e.getStatus() == IStatus.ACTIVE));
		dto.setId(e.getId());
		dto.setDepartment(toDto(e.getDepartment()));
		dto.setEndDate(e.getEndDate());
		dto.setFunction(toDto(e.getFunction()));
		dto.setPerson(toDto(e.getPerson()));
		dto.setStartDate(e.getStartDate());

		
		return dto;
	}
	
	public List<DepartmentPositionDTO> departmentPositionListToDto(List<DepartmentPosition> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<DepartmentPositionDTO> list = new ArrayList<>();
		
		for(DepartmentPosition i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	
	
	public EmployeePeriodDTO toDto(EmploymentPeriod e)
	{
		if(e == null) return null;
		
		EmployeePeriodDTO dto = new EmployeePeriodDTO();
		dto.setAvgSalary(e.getAvgSalary());
		dto.setFromDate(e.getFromDate());
		dto.setId(e.getId());
		dto.setMaxSalary(e.getMaxSalary());
		dto.setMinSalary(e.getMinSalary());
		dto.setNid(e.getNid());
		dto.setNipt(e.getNipt());
		dto.setProfession(e.getProfession());
		dto.setSubject(e.getSubject());
		dto.setToDate(e.getToDate());

		
		return dto;
	}
	
	public List<EmployeePeriodDTO> employmentPeriodListToDto(List<EmploymentPeriod> data)
	{
		if(data == null || data.isEmpty()) return null;
		
		List<EmployeePeriodDTO> list = new ArrayList<>();
		
		for(EmploymentPeriod i : data)
		{
			list.add(toDto(i));
		}
		
		return list;
		
	}
	

}
