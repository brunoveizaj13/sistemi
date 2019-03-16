package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.PoiDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.services.HelperService;
import com.brunoveizaj.sistemi.ui.services.PatronageService;
import com.brunoveizaj.sistemi.ui.services.QvStatsService;
import com.brunoveizaj.sistemi.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewQvStatBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	String header;
	
	List<PersonDTO> persons;
	
	List<PatronageDTO> qvPatronages;
	List<PoiDTO> qvPois;
	
	
	Integer regionId;
	Integer municipId;
	Integer unitId;
	
	List<QvDTO> qvs;
	
	Integer qvId;
	Integer lastQvId;
	
	
	
	
	public void listPersons()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Lista e votuesve";
		this.persons = new QvStatsService().persons(qvId);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	public void listPatronage()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Lista e patronazhisteve";
		this.persons = new QvStatsService().patronages(qvId, IPatronageType.PERSON);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	public void listInPatronage()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Te patronazhuar";
		this.persons = new QvStatsService().personsInPatronage(qvId, IPatronageType.PERSON);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	public void listNotInPatronage()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Te pa patronazhuar";
		this.persons = new QvStatsService().personsNotInPatronage(qvId);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	public void listInstPatronage()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Patronazhiste ne Institucione";
		this.persons = new QvStatsService().patronages(qvId, IPatronageType.INSTITUTION);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	 
	public void listInInstitutionPatronage()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Te patronazhuar ne instiitucione";
		this.persons = new QvStatsService().personsInPatronage(qvId, IPatronageType.INSTITUTION);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	public void listFirstTimeVoters()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Votues per here te pare";
		this.persons = new QvStatsService().firstTimeVoters(qvId);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	public void listNotVoting()
	{
		if(qvId == null)
		{
			Messages.throwFacesMessage("Zgjidhni QV-ne",3);
			return;
		}
		this.header = "Pa votuar 2017";
		this.persons = new QvStatsService().notVotingLastElections(qvId);
		if(persons == null || persons.isEmpty())
		{
			Messages.throwFacesMessage("NUk u gjet asnje person", 2);
		}
		loadInteres();
		this.lastQvId = qvId;
	}
	
	
	public void loadInteres()
	{
		if(lastQvId == null || (lastQvId != qvId))
		{
			this.qvPatronages = new PatronageService().getPatronagesByArea(null, qvId, IPatronageType.PERSON);
			this.qvPois = null;
		}
	}
	
	
	
	public List<PatronagePersonDTO> getPersonPatronages(PersonDTO p)
	{
		return new PatronageService().getPatronagesOfPerson(p.getNid(), IPatronageType.PERSON);
	}
	
	
	
	public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.setFitToPage(true);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
        }        
        
    }
    
	
	public void onRegionSelect()
	{
		this.municipId = null;
		this.unitId = null;
		this.qvId = null;
	}
	
	
	public void onMunicipalitySelect()
	{
		this.unitId = null;
		this.qvId = null;
	}
	
	public void onUnitSelect()
	{
		if(unitId == null) 
		{
			this.qvs = null;
			return;
		}
		
		this.qvs = new HelperService().listQv(null, null, unitId, null);
		this.qvId = null;
	}

}
