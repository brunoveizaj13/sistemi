package com.brunoveizaj.sistemi.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.dto.AddressDTO;
import com.brunoveizaj.sistemi.dto.BuildingDTO;
import com.brunoveizaj.sistemi.dto.EmployeeDTO;
import com.brunoveizaj.sistemi.dto.InstitutionDTO;
import com.brunoveizaj.sistemi.dto.MunicipalityDTO;
import com.brunoveizaj.sistemi.dto.PartyDTO;
import com.brunoveizaj.sistemi.dto.PatronageDTO;
import com.brunoveizaj.sistemi.dto.PatronagePersonDTO;
import com.brunoveizaj.sistemi.dto.PatronageTypeDTO;
import com.brunoveizaj.sistemi.dto.PersonDTO;
import com.brunoveizaj.sistemi.dto.PoiTypeDTO;
import com.brunoveizaj.sistemi.dto.QvDTO;
import com.brunoveizaj.sistemi.dto.RegionDTO;
import com.brunoveizaj.sistemi.dto.RoleDTO;
import com.brunoveizaj.sistemi.dto.SubjectDTO;
import com.brunoveizaj.sistemi.dto.UnitDTO;
import com.brunoveizaj.sistemi.dto.UserDTO;
import com.brunoveizaj.sistemi.entities.Address;
import com.brunoveizaj.sistemi.entities.Building;
import com.brunoveizaj.sistemi.entities.Employee;
import com.brunoveizaj.sistemi.entities.Institution;
import com.brunoveizaj.sistemi.entities.Municipality;
import com.brunoveizaj.sistemi.entities.Party;
import com.brunoveizaj.sistemi.entities.Patronage;
import com.brunoveizaj.sistemi.entities.PatronagePerson;
import com.brunoveizaj.sistemi.entities.PatronageType;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.entities.PoiType;
import com.brunoveizaj.sistemi.entities.Qv;
import com.brunoveizaj.sistemi.entities.Region;
import com.brunoveizaj.sistemi.entities.Role;
import com.brunoveizaj.sistemi.entities.Subject;
import com.brunoveizaj.sistemi.entities.Unit;
import com.brunoveizaj.sistemi.entities.User;
import com.brunoveizaj.sistemi.utils.DateUtil;

public class Assembler {
	
	
	
	public PersonDTO toDto(Person e)
	{
		if(e == null) return null;
		
		PersonDTO dto = new PersonDTO();
		
		dto.setDapStatus(e.getDapStatus() != null && e.getDapStatus().intValue() == IStatus.ACTIVE);
		dto.setDob(DateUtil.formatReverseToNormalDate(e.getDob()));
		dto.setEconomicHelpStatus(e.getEconomicHelpStatus() != null && e.getEconomicHelpStatus().intValue() == IStatus.ACTIVE);
		dto.setEmail(e.getEmail());
		dto.setFamilyId(e.getFamilyId()==null?0:e.getFamilyId().longValue());
		dto.setFamilyRelation(e.getFamilyRelation());
		dto.setFatherName(e.getFatherName());
		dto.setFirstTimeVoterStatus(e.getFirstTimeVoterStatus() != null && e.getFirstTimeVoterStatus().intValue() == IStatus.ACTIVE);
		dto.setFraction(e.getFraction());
		dto.setGender(e.getGender());
		dto.setMaidenName(e.getMaidenName());
		dto.setMemberStatus(e.getMemberStatus() != null && e.getMemberStatus().intValue() == IStatus.ACTIVE);
		dto.setMotherName(e.getMotherName());
		dto.setName(e.getName());
		dto.setNid(e.getNid());
		dto.setPatronageInstitutionStatus(e.getPatronageInstitutionStatus() != null && e.getPatronageInstitutionStatus().intValue() == IStatus.ACTIVE);
		dto.setPatronageStatus(e.getPatronageStatus() != null && e.getPatronageStatus().intValue() == IStatus.ACTIVE);
		dto.setPhone(e.getPhone());
		dto.setPoiStatus(e.getPoiStatus() != null && e.getPoiStatus().intValue() == IStatus.ACTIVE);
		dto.setQv(toDto(e.getQv()));
		dto.setQvAddress(e.getQvAddress());
		dto.setSocialHouseStatus(e.getSocialHouseStatus() != null && e.getSocialHouseStatus().intValue() == IStatus.ACTIVE);
		dto.setSubjectPartyCode(e.getSubjectPartyCode());
		dto.setSurname(e.getSurname());
		dto.setVotingNo(e.getVotingNo()==null?0:e.getVotingNo().intValue());
		dto.setVoting2017Status(e.getVoting2017Status() != null && e.getVoting2017Status().intValue() == IStatus.ACTIVE);
		
		
		
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
	
  

}
