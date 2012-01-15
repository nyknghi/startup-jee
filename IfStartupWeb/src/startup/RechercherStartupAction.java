package startup;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class RechercherStartupAction extends org.apache.struts.action.Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {	
		List<Startup> startups = null;
		RechercherStartupForm rechercherStartupForm = (RechercherStartupForm) form;
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();
		
		if (rechercherStartupForm.getNom() == "" && rechercherStartupForm.getActivite() != "") {
			startups = remoteEvents.findStartupByActivity(rechercherStartupForm.getActivite());
		}
		if (rechercherStartupForm.getNom() != "" && rechercherStartupForm.getActivite() == "") {
			startups = remoteEvents.findStartupByName(rechercherStartupForm.getNom());
		}
		if (rechercherStartupForm.getNom() != "" && rechercherStartupForm.getActivite() != "") {
			startups = remoteEvents.findStartupByCritere(rechercherStartupForm.getNom(), rechercherStartupForm.getActivite());
		}			
		request.setAttribute("startups", startups);
		return mapping.findForward("success");
	}	
}
