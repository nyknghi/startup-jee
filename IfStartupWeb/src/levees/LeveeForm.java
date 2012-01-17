package levees;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LeveeForm extends org.apache.struts.action.ActionForm{
    private String cible;
    private String startup;
    private ArrayList startupList;

    public ArrayList getStartupList() {
        return startupList;
    }

    public void setStartupList(ArrayList startupList) {
        this.startupList = startupList;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
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
