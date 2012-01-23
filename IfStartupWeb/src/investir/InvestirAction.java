/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package investir;

import com.vaannila.BeanUtil;

import gestion_events.Etape;
import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.Investisseur;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author UTILISATEUR
 */
public class InvestirAction extends org.apache.struts.actions.DispatchAction{
    
    public ActionForward Forward(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InvestirForm f = (InvestirForm)form;
        String login = (String)request.getSession().getAttribute("login");
        try{
        	String i="";
	        String s = Long.toString(BeanUtil.getEvents().findStartupByName(f.getStartup()).get(0).getIdStartup());
	        if(request.getSession().getAttribute("User").equals("BA")){
	        	i = Long.toString(BeanUtil.getInvestisseurs().rechercherBAParMail(login).getIdInvestisseur());
	        }else if(request.getSession().getAttribute("User").equals("investisseur")){
	        	i = Long.toString(BeanUtil.getInvestisseurs().rechercherInvestisseurParMail(login).getIdInvestisseur());
	        }
	        request.setAttribute("idlevee", f.getIdLevee());
	        request.setAttribute("idinv", i);
	        request.setAttribute("idstart", s);
	        return mapping.findForward("success");
        }catch(Exception e){
        	return mapping.findForward("error");
        }
    }
    
    public ActionForward Participer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InvestirForm f = (InvestirForm)form;
        try{
        	LeveeDeFonds lev = BeanUtil.getEvents().findLeveeDeFonds(Long.valueOf(f.getIdLevee()));
        	if(lev.getEtape()==Etape.ENGAGEMENT){
	        	if(request.getSession().getAttribute("User").equals("BA")){
	        		BeanUtil.getEvents().participation(lev, BeanUtil.getInvestisseurs().rechercherBAParId(Long.valueOf(f.getInvestisseur())), Double.valueOf(f.getMontant()));
	        		return mapping.findForward("success-BA");
	        	}else{
	        		BeanUtil.getEvents().participation(lev, BeanUtil.getInvestisseurs().rechercherInvestisseurParId(Long.valueOf(f.getInvestisseur())), Double.valueOf(f.getMontant()));
	        		return mapping.findForward("success-inv");
	        	}
        	}else{
        		return mapping.findForward("error-etape");
        	}
        }catch(Exception e){
        	return mapping.findForward("error");
        }
    }
    
}
