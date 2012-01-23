/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inscription;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author UTILISATEUR
 */
public class InscriptionForm extends org.apache.struts.action.ActionForm {
    
    private String idLevee="0";

    public String getIdLevee() {
        return idLevee;
    }

    public void setIdLevee(String idLevee) {
        this.idLevee = idLevee;
    }
    
    /*@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (idLevee == null || idLevee.length() < 1) {
            errors.add("levee", new ActionMessage("error.levee.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }*/
}
