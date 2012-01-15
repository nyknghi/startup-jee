package investisseur;

import gestion_events.Startup;
import gestion_investisseurs.Fondateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class FondateurAction extends org.apache.struts.action.Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Fondateur f = BeanUtil.getInvestisseurs().findFondateurByEmail((String)request.getSession().getAttribute("login"));
		Startup startup = f.getStartup();
		request.setAttribute("startup", startup);
		return mapping.findForward("success");
	}
}
