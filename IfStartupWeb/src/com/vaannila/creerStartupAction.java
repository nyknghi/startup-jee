package com.vaannila;

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

import util.Couple;

public class creerStartupAction extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	creerStartupForm creerForm = (creerStartupForm) form;
    	
    	
		try{
			Context ctx = new InitialContext();
			ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");
			ctx.addToEnvironment(InitialContext.PROVIDER_URL,
					"jnp://localhost:1099");
			
			RemoteInvestisseurs remoteInv = (RemoteInvestisseurs) ctx.lookup("BeanInvestisseurs/remote");
			EventsBeanRemote remoteEvents = (EventsBeanRemote) ctx.lookup("EventsBean/remote");
			
			
			Fondateur f = remoteInv.creerFondateur("Dupont", "dupont@gmail.com", "12345");
			Couple<Startup, Fondateur> res = remoteEvents.startup(creerForm.getNom(), creerForm.getActivite(), f);
			Startup s = res.getObjetA();
			f = res.getObjetB();
			//f.setStartup(s);
			
			//System.out.println(s);
//			System.out.println(f);
			
			//remote.closeEM();
			
		}catch (NamingException e){
			e.printStackTrace();
		}    	
    	
    	return mapping.findForward("success");
    }
}
