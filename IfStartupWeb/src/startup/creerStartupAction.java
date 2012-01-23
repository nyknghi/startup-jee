package startup;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.vaannila.BeanUtil;

import util.Couple;

public class creerStartupAction extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    		creerStartupForm creerForm = (creerStartupForm) form;  	
    		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
    		EventsBeanRemote remoteEvents = BeanUtil.getEvents();
    		if(request.getSession().getAttribute("User").equals("fondateur")){
	    		Fondateur f = (Fondateur)BeanUtil.getInvestisseurs().findFondateurByEmail((String)request.getSession().getAttribute("login"));
				Couple<Startup, Fondateur> res = remoteEvents.startup(creerForm.getNom(), creerForm.getActivite(), f);
				Startup s = res.getObjetA();
				f = res.getObjetB();
    		}else{
    			remoteEvents.Startup(creerForm.getNom(), creerForm.getActivite());
    		}
    	return mapping.findForward("success");
    }
}
