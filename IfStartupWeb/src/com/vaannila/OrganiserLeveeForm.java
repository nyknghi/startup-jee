package com.vaannila;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import java.util.List;


public class OrganiserLeveeForm extends org.apache.struts.action.ActionForm{
    private String date;
    private String cible;
    private String startup;
    private List startupList;

    public List getStartupList() {
		return startupList;
	}

	public void setStartupList(List startupList) {
		this.startupList = startupList;
	}

	public String getCible() {
        return cible;
    }

    public String getDate() {
        return date;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartup() {
        return startup;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(getStartup()==null || getStartup().isEmpty()){
            errors.add("organisateur", new ActionMessage("Spécifiez la startup qui organise l'evenement"));
        }else if(getCible()==null || getCible().isEmpty()){
            errors.add("cible", new ActionMessage("Spécifiez le montant cible à collecter"));
        }else{
            try{
                double d = Double.valueOf(getCible());
            }catch(Exception e){
                errors.add("cible", new ActionMessage("Spécifiez un montant valide"));
            }
        }
        return errors;
    }
}
