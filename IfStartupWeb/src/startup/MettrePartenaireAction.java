package startup;

import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class MettrePartenaireAction extends org.apache.struts.action.Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {	
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		MettrePartenaireForm mettrePartenaireForm = (MettrePartenaireForm) form;
		
		Fondateur f = (Fondateur)BeanUtil.getInvestisseurs().findFondateurByEmail((String)request.getSession().getAttribute("login"));

		String sId = request.getParameter("id");
		ClubAmi ca = BeanUtil.getInvestisseurs().rechercherClubParId(Long.parseLong(sId));
		remoteInv.mettreEnPartenaire(ca, f.getStartup());
		//mettrePartenaireForm.setMessage("Startup " + f.getStartup().getNomStartup() + " a mis en partenaire avec le Club Ami " + ca.getNomClub());
		return mapping.findForward("success");
	}
}
