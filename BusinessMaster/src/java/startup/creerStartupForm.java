/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author UTILISATEUR
 */
public class creerStartupForm extends org.apache.struts.action.ActionForm {
    
    private String nom;
    private String capital;
    private String activite;

    /**
     *
     */
    public creerStartupForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getActivite() {
        return activite;
    }

    public String getCapital() {
        return capital;
    }

    public String getNom() {
        return nom;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getNom()==null || getNom().equals("")) {
            errors.add("name", new ActionMessage("error.name.required"));
        }else if(getActivite()==null || getActivite().equals("")){
            errors.add("activite", new ActionMessage("error.activite.required"));
        }
        return errors;
    }
}
