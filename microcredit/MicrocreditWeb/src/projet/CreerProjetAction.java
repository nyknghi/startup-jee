package projet;
import gestion_microcredit;
import gestion_microcredit.MicrocreditBeanRemote;
import gestion_microcredit.Projet;
import gestion_microcredit.Entrepreneur;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;


public class CreerProjetAction extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    		creerProjetForm creerForm = (creerProjetForm) form;  	
    		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
    		MicrocreditBeanRemote remoteEvents = BeanUtil.getEvents();
    		if(request.getSession().getAttribute("User").equals("fondateur")){
	    		Entrepreneur e = (Entrepreneur)BeanUtil.getInvestisseurs().findFondateurByEmail((String)request.getSession().getAttribute("login"));
				Couple<Projet, Entrepreneur> res = remoteEvents.startup(creerForm.getNom(), creerForm.getActivite(), f);
				Startup s = res.getObjetA();
				f = res.getObjetB();
    		}else{
    			remoteEvents.Startup(creerForm.getNom(), creerForm.getActivite());
    		}
    	return mapping.findForward("success");
    }   {

}
