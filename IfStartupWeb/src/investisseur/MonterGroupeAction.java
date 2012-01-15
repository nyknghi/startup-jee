package investisseur;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.GroupeInvestisseurs;
import gestion_investisseurs.Investisseur;
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

public class MonterGroupeAction extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	MonterGroupeForm monterGroupeForm = (MonterGroupeForm) form;  	
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();
		
		Investisseur inv = BeanUtil.getInvestisseurs().rechercherInvestisseurParMail((String)request.getSession().getAttribute("login"));
		Couple<GroupeInvestisseurs, Investisseur> res = remoteInv.monterGroupe(inv, monterGroupeForm.getNom());
    	return mapping.findForward("success");
    }
}
