package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.forms.DepartmentForm;
import com.brunoveizaj.sistemi.ui.forms.DepartmentPositionForm;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.DepartmentDTO;
import com.brunoveizaj.sistemi.ui.models.DepartmentFunctionDTO;
import com.brunoveizaj.sistemi.ui.models.DepartmentPositionDTO;
import com.brunoveizaj.sistemi.ui.models.OrganigramModel;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.services.DepartmentService;
import com.brunoveizaj.sistemi.ui.services.PersonService;
import com.brunoveizaj.sistemi.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewMunicipPartyStrBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	
	
	
	private OrganigramNode rootNode;
    private OrganigramNode selection;
 
    private boolean zoom = false;
    private int leafNodeConnectorHeight = 0;
    private boolean autoScrollToSelection = false;
 

    List<DepartmentFunctionDTO> functions;
    
    DepartmentForm departmentForm;
    
    
    List<PersonDTO> persons;
    PersonDTO selectedPerson;
    DepartmentPositionForm deptPositionForm;
    
    Integer functionId;
    
    
    List<DepartmentPositionDTO> departmentPositions;
    
    
    
    @PostConstruct
    public void load()
    {
    	this.functions = new DepartmentService().listDepartmentFunctions();
    }
    
    
    public void init() {
    	 
    	String municipId = nav.getParam("municip_id");
    	
    	Integer munId = Integer.valueOf(municipId);
    	
    	DepartmentDTO root = new DepartmentService().getMunicipalityRootDepartment(munId);
    	if(root == null)
    	{
    		OrganigramModel o = new OrganigramModel();
    		o.setDepartmentName("Struktura e pa percaktuar");
    		o.setName("Struktura e pa percaktuar");
    		rootNode = new DefaultOrganigramNode("root", o, null);
            rootNode.setCollapsible(false);
            rootNode.setDroppable(false);
    		return;
    	}
    	DepartmentPositionDTO dp = new DepartmentService().getDepartmentSinglePosition(root.getId());
    	OrganigramModel o = (dp == null)? new OrganigramModel(root) : new OrganigramModel(dp);
        rootNode = new DefaultOrganigramNode("root", o, null);
        rootNode.setCollapsible(false);
        rootNode.setDroppable(false);
 
        List<DepartmentDTO> childs = new DepartmentService().getChildDepartments(root.getId());
        
        if(childs != null && !childs.isEmpty())
        {
	        for(DepartmentDTO d : childs)
	        {
	        	addChilds(rootNode, d);
	        }
        } 
        

    }
    
    
    public void addChilds(OrganigramNode parent, DepartmentDTO data)
    {
    	DepartmentPositionDTO dp = null;
    	if(data.isSinglePosition())
    	{
        	dp = new DepartmentService().getDepartmentSinglePosition(data.getId());
    	}
    	OrganigramModel o = (dp == null)? new OrganigramModel(data) : new OrganigramModel(dp);
    	
    	OrganigramNode divisionNode = new DefaultOrganigramNode("division", o, parent);
    	divisionNode.setSelectable(true);
    	divisionNode.setExpanded(o.isExpanded());
    	List<DepartmentDTO> childs = new DepartmentService().getChildDepartments(data.getId());
    	if(childs != null && !childs.isEmpty())
    	{
	    	for(DepartmentDTO d : childs)
	    	{
	    		addChilds(divisionNode, d);
	    	}
    	}
    }
    
    public void prepareAddDepartment()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramModel o = (OrganigramModel)currentSelection.getData();
        
        this.departmentForm = new DepartmentForm();
        this.departmentForm.setParentId(o.getDepartmentId());
        
    }
    
    public void addDepartment()
    {
    	try {
    		
	    	DepartmentDTO d = new DepartmentService().registerDepartment(this.departmentForm);
	    	
	    	OrganigramModel o = new OrganigramModel(d);
	    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	    	new DefaultOrganigramNode("division", o, currentSelection);
    	    Messages.throwFacesMessage("Departamenti u shtua me sukses", 1);
    	    
    	}catch(Exception e)
    	{
    		Messages.throwFacesMessage(e);
    	}
    }
    
    
    public void prepareAddPosition()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramModel o = (OrganigramModel)currentSelection.getData();
        
        this.functionId = null;
        this.deptPositionForm = new DepartmentPositionForm();
        this.deptPositionForm.setDepartmentId(o.getDepartmentId());
        
    }
    
    public void addPosition()
    {
    	try {
    		
    		if(this.selectedPerson == null)
    		{
    			Messages.throwFacesMessage("Zgjidhni personin",2);
    			return;
    		}
    		
    		this.deptPositionForm.setPersonNid(selectedPerson.getNid());
    		this.deptPositionForm.setFunctionId(functionId);
	    	DepartmentPositionDTO d = new DepartmentService().registerDepartmentPosition(this.deptPositionForm);
	    	if(d.getDepartment().isSinglePosition()) {
		    	OrganigramModel o = new OrganigramModel(d);
		    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		    	currentSelection.setData(o);
	    	}
	    	
    	    Messages.throwFacesMessage("Personi u shtua me sukses", 1);
    	    
    	}catch(Exception e)
    	{
    		Messages.throwFacesMessage(e);
    	}
    }
    
    public List<PersonDTO> searchPerson(String query)
    {
    	if (       query.length() > 3 
    			&& query.contains(" ") 
    			&& (query.length() > (query.indexOf(" ")+1)) 
    			&& Character.isLetter(query.charAt(query.indexOf(" ")+1)))
	    {
	    	String[] names = query.split(" ",-1);
	    	
	    	String name = null;
	    	String fatherName = null;
	    	String surname = null;
	    	
	    	if(names != null && names.length > 1)
	    	{
	    		name = names[0];
	    		if(names.length == 2)
	    		{
	    			surname = names[1];
	    			
	    		}
	    		else
	    		{
	    			fatherName = names[1];
	    			surname = names[2];
	    		}
	    	}
	    	
	    	PersonSx sx = new PersonSx();
	    	sx.setName(name+"%");
	    	sx.setSurname(surname+"%");
	    	sx.setFatherName(fatherName==null?null:(fatherName+"%"));
	    	List<PersonDTO> pers = new PersonService().searchPerson(sx);		    	
	    	
	    	this.persons = pers;
	    	
	    	return pers;
	    	
	    }
    	
    	return null;
    }
    
    
    public void listDepartmentPersons()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
    	
    	OrganigramModel data = (OrganigramModel)currentSelection.getData();
    	if(data == null)
    	{
    		Messages.throwFacesMessage("Nuk ka persona", 2);
    		return;
    	}
    		    	
    	this.departmentPositions = new DepartmentService().getDepartmentPositions(data.getDepartmentId());
    }
    
    public void listDepartmentPositionsHistory()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
    	
    	OrganigramModel data = (OrganigramModel)currentSelection.getData();
    	if(data == null)
    	{
    		Messages.throwFacesMessage("Nuk ka persona", 2);
    		return;
    	}
    		    	
    	this.departmentPositions = new DepartmentService().getDepartmentPositionsHistory(data.getDepartmentId());
    }
    
    
    public void expandDepartmentPersons()
    {    		
	    	
	    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	    	
	    	OrganigramModel data = (OrganigramModel)currentSelection.getData();
	    	if(data == null)
	    	{
	    		Messages.throwFacesMessage("Nuk ka persona", 2);
	    		return;
	    	}
	    		    	
	    	List<DepartmentPositionDTO> posList = new DepartmentService().getDepartmentPositions(data.getDepartmentId());
	    	
	    	if(posList == null || posList.isEmpty())
	    	{
	    		Messages.throwFacesMessage("Nuk ka persona", 2);
	    		return;
	    	}
	    	
	    	for(DepartmentPositionDTO p : posList)
	    	{
	    		new DefaultOrganigramNode("employee", new OrganigramModel(p), currentSelection);
	    	}
	    	
    }
    
    public void collapseDepartmentPersons()
    {    		
	    	
	    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	    	currentSelection.setChildren(null);
	    	
    }
    
    
    
    public void removeDivision() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getData();
    }
 
    public void removePerson() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getParent().getChildren().remove(currentSelection);
    }
 
 
    
    
	
	

}
