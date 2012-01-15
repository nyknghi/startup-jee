package startup;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class RechercherClubAmiForm extends org.apache.struts.action.ActionForm {
	private String nomclub;
	private String startup;
	private ArrayList startupList;

	public ArrayList getStartupList() {
		return startupList;
	}

	public void setStartupList(ArrayList startupList) {
		this.startupList = startupList;
	}

	public String getStartup() {
		return startup;
	}

	public void setStartup(String startup) {
		this.startup = startup;
	}

	public String getNomclub() {
		return nomclub;
	}

	public void setNomclub(String nomclub) {
		this.nomclub = nomclub;
	}
	
}
