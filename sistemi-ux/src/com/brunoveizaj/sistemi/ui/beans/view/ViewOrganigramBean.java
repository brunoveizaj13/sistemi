package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewOrganigramBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
		
	
	
	private OrganigramNode rootNode;
    private OrganigramNode selection;
 
    private boolean zoom = false;
   // private String style = "width: 800px";
    private int leafNodeConnectorHeight = 0;
    private boolean autoScrollToSelection = false;
 
    private String employeeName;
 
    @PostConstruct
    public void init() {
    	
        selection = new DefaultOrganigramNode(null, "Ridvan Agar", null);
 
        rootNode = new DefaultOrganigramNode("root", "Kryetari i Partisë", null);
        rootNode.setCollapsible(false);
        rootNode.setDroppable(true);
 
 
        OrganigramNode partyCongress = addDivision(rootNode, "Kongresi i partise");
 
        OrganigramNode nationalAssambe = addDivision(partyCongress, "Asambleja Kombetare PS");
        OrganigramNode psLidership = addDivision(nationalAssambe, "Kryesia PS");
        addDivision(nationalAssambe, "Sekretariati Ekzekutiv");
        addDivision(nationalAssambe, "Kryefinancieri");
        addDivision(nationalAssambe, "Komisioni Zgjedhjeve PS");
        addDivision(nationalAssambe, "Komisionet e AK");
 
        addDivision(partyCongress, "Komisioni G.S.E");
        
        OrganigramNode regionCouncil = addDivision(psLidership, "Keshilli koordinues i qarkut");
        OrganigramNode municipAssamble = addDivision(regionCouncil, "Asambleja Socialiste e Bashkise");
        addDivision(municipAssamble, "Kryetaret dhe sekretaret");
        OrganigramNode unitCouncil = addDivision(municipAssamble, "Keshilli Socialiste e Nj.A");
        addDivision(unitCouncil, "Kryetari KS te Nj.A");
        addDivision(unitCouncil, "Organizata Socialiste");

    }
 
    
    protected OrganigramNode addDivision(OrganigramNode parent, Object data) {
        OrganigramNode divisionNode = new DefaultOrganigramNode("division", data, parent);
        divisionNode.setDroppable(true);
        divisionNode.setDraggable(true);
        divisionNode.setSelectable(true);
       
 
        return divisionNode;
    }
    
    
    protected OrganigramNode addDivision(OrganigramNode parent, String name, String... employees) {
        OrganigramNode divisionNode = new DefaultOrganigramNode("division", name, parent);
        divisionNode.setDroppable(true);
        divisionNode.setDraggable(true);
        divisionNode.setSelectable(true);
 
        if (employees != null) {
            for (String employee : employees) {
                OrganigramNode employeeNode = new DefaultOrganigramNode("employee", employee, divisionNode);
                employeeNode.setDraggable(true);
                employeeNode.setSelectable(true);
            }
        }
 
        return divisionNode;
    }
 
    public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() 
        		+ "' moved from " + event.getSourceOrganigramNode().getData() 
        		+ " to '" + event.getTargetOrganigramNode().getData() + "'");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
 
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void nodeSelectListener(OrganigramNodeSelectEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' selected.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
 
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' collapsed.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
 
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void nodeExpandListener(OrganigramNodeExpandEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' expanded.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
 
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void removeDivision() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        setMessage(currentSelection.getData() + " will entfernt werden.", FacesMessage.SEVERITY_INFO);
    }
 
    public void removeEmployee() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getParent().getChildren().remove(currentSelection);
    }
 
    public void addEmployee() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
 
        OrganigramNode employee = new DefaultOrganigramNode("employee", employeeName, currentSelection);
        employee.setDraggable(true);
        employee.setSelectable(true);
    }
 
    private void setMessage(String msg, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage();
        message.setSummary(msg);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	
	
	
	
	
}
