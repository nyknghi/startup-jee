package fondateur;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class InvestirFondateurForm extends org.apache.struts.action.ActionForm{

	private String startup;
	private String montant;
	
	public String getStartup() {
		return startup;
	}
	public void setStartup(String startup) {
		this.startup = startup;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if(startup==null || startup.length()<0){
			errors.add("startup", new ActionMessage("Startup requise"));
		}else if(montant==null || montant.length()<0){
			errors.add("montant", new ActionMessage("montant requis"));
		}
		return errors;
	}
}
