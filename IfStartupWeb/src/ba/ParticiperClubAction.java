package ba;

import java.util.List;

import gestion_events.EventsBeanRemote;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;

import com.vaannila.BeanUtil;

import util.Couple;

public class ParticiperClubAction extends DispatchAction {
    public ActionForward populate(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	List<ClubAmi> clubs = null;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();    
    	
		clubs = remoteInv.findAllClub();	
		request.setAttribute("clubs", clubs);   	
    	return mapping.findForward("success");
    }
    
    public ActionForward participer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	ParticiperClubForm participerClubForm = (ParticiperClubForm) form;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();    
		
		String sId = request.getParameter("id");
//		ClubAmi club = BeanUtil.getInvestisseurs().rechercherClubParId(Long.parseLong(sId));
		BusinessAngel ba = BeanUtil.getInvestisseurs().rechercherBAParMail((String)request.getSession().getAttribute("login"));
		Couple<ClubAmi, BusinessAngel> res = remoteInv.ajouterMembre(ba, Long.parseLong(sId), false);
    	return mapping.findForward("return");
    }
    
    public ActionForward quitter(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	ParticiperClubForm participerClubForm = (ParticiperClubForm) form;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();    
		
		String sId = request.getParameter("id");
		ClubAmi club = BeanUtil.getInvestisseurs().rechercherClubParId(Long.parseLong(sId));
		BusinessAngel ba = BeanUtil.getInvestisseurs().rechercherBAParMail((String)request.getSession().getAttribute("login"));
		Couple<ClubAmi, BusinessAngel> res = remoteInv.supprimerMembre(ba, club);
    	return mapping.findForward("return");
    }    
}
