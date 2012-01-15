package startup;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.RemoteInvestisseurs;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import util.Couple;

import com.vaannila.BeanUtil;

public class MettrePartenaireAction extends org.apache.struts.action.Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {	
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();
		MettrePartenaireForm mettrePartenaireForm = (MettrePartenaireForm) form;
		
		Fondateur f = (Fondateur)BeanUtil.getInvestisseurs().findFondateurByEmail((String)request.getSession().getAttribute("login"));
//		Fondateur f = remoteInv.findFondateurByEmail("dupont@gmail.com");
//		Fondateur f = remoteInv.findFondateurByEmail("tartempion@gmail.com");
		String sId = request.getParameter("id");
		ClubAmi ca = BeanUtil.getInvestisseurs().rechercherClubParId(Long.parseLong(sId));
		Couple<ClubAmi, Startup> res = remoteInv.mettreEnPartenaire(ca, f.getStartup());
		mettrePartenaireForm.setMessage("Startup " + f.getStartup().getNomStartup() + " a mis en partenaire avec le Club Ami " + ca.getNomClub());
		return mapping.findForward("success");
	}
}
