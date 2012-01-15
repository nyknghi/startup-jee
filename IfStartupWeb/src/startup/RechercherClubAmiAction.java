package startup;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.ClubAmi;
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
import org.apache.struts.util.LabelValueBean;

import com.vaannila.BeanUtil;

public class RechercherClubAmiAction extends org.apache.struts.action.Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {	
		List<ClubAmi> clubamis = null;
//		List<Startup> startups = null;
//		ArrayList startupList = new ArrayList();
		RechercherClubAmiForm rechercherClubAmiForm = (RechercherClubAmiForm) form;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();
		
		clubamis = remoteInv.rechercherClubParNom(rechercherClubAmiForm.getNomclub());
//		startups = remoteEvents.findAllStartup();
		request.setAttribute("clubamis", clubamis);
//		Startup[] aStartups = startups.toArray(new Startup[startups.size()]);
//		for (int i=0; i<startups.size(); i++){
//			int t = i + 1;
//			String si = "" + t;
//			startupList.add(new LabelValueBean(aStartups[i].getNomStartup(), si));
//		}
//		rechercherClubAmiForm.setStartupList(startupList);		
		return mapping.findForward("success");
	}
}
