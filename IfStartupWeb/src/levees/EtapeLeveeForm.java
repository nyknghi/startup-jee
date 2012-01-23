package levees;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EtapeLeveeForm extends org.apache.struts.action.ActionForm{

	private String idLevee;

	public String getIdLevee() {
		return idLevee;
	}

	public void setIdLevee(String idLevee) {
		this.idLevee = idLevee;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if(idLevee==null || idLevee.isEmpty()){
			errors.add("levee", new ActionMessage("Specifiez la levee!"));
		}
		return errors;
	}
}
