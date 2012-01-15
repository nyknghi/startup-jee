package ba;

import gestion_events.EventsBeanRemote;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.ClubAmi;
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

public class MonterClubAction extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	MonterClubForm monterClubForm = (MonterClubForm) form;  	
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();
		
		BusinessAngel ba = (BusinessAngel)BeanUtil.getInvestisseurs().rechercherBAParMail((String)request.getSession().getAttribute("login"));
//		BusinessAngel ba = remoteInv.creerBA("Dung", "dung@gmail.com", "dung");
//		Fondateur f = remoteInv.creerFondateur("Dung", "dungnh@gmail.com", "12345");
		ClubAmi res = remoteInv.monterClubAmi(ba, monterClubForm.getNom());
    	return mapping.findForward("success");
    }
}
