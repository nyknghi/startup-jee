package levees;

import gestion_events.LeveeDeFonds;
import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.Fondateur;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class EtapeLeveeAction extends org.apache.struts.action.Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String login = (String)request.getSession().getAttribute("login");
		try{
		ArrayList<LeveeDeFonds> levees = (ArrayList<LeveeDeFonds>)BeanUtil.getEvents().findAllLevees();
		AbstraitInvestisseur inv;
		if(request.getSession().getAttribute("User").equals("BA")){
			inv = (BusinessAngel)BeanUtil.getInvestisseurs().rechercherBAParMail(login);
		}else{
			inv = (Fondateur)BeanUtil.getInvestisseurs().findFondateurByEmail(login);
		}
		ArrayList list = new ArrayList();
		for(LeveeDeFonds l: levees){
			if(l.getOrganisateur().getIdInvestisseur()==inv.getIdInvestisseur()){
				list.add(new EtapeLeveeBean((Long.valueOf(l.getIdLevee())).toString(), l.getDate_levee().toString(), l.getStartup().getNomStartup(), l.getEtape().toString(), l.getMontantCible(), BeanUtil.getEvents().totalParticipations(l)));
			}
		}
		request.setAttribute("list_levees_etape", list);
		return mapping.findForward("success");
		}catch(Exception e){
			return mapping.findForward("error");
		}
	}
}
