package startup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class creerStartupForm extends org.apache.struts.action.ActionForm {
	private String nom;
	private String capital;
	private String activite;
	
	public creerStartupForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (nom == null || nom.length() < 1) {
            errors.add("nom", new ActionMessage("error.userName.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        if (activite == null || activite.length() < 1) {
            errors.add("activite", new ActionMessage("error.password.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
    
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
	
}
