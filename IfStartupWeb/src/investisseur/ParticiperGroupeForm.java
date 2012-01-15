package investisseur;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ParticiperGroupeForm extends org.apache.struts.action.ActionForm {
	private String groupe;
	private ArrayList groupeList;
	private Boolean checkboxValue;
	
	public Boolean getCheckboxValue() {
		return checkboxValue;
	}
	public void setCheckboxValue(Boolean checkboxValue) {
		this.checkboxValue = checkboxValue;
	}
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	public ArrayList getGroupeList() {
		return groupeList;
	}
	public void setGroupeList(ArrayList groupeList) {
		this.groupeList = groupeList;
	}
	
}
