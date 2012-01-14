package investisseur;

import gestion_events.EventsBeanLocal;
import gestion_events.EventsBeanRemote;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class CreerClubAmiAction extends org.apache.struts.action.Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	CreerClubAmiForm creerClubForm = (CreerClubAmiForm) form;
    	
    	RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
    	EventsBeanRemote remoteEvent = BeanUtil.getEvents();
    	
    	
    	
    	return mapping.findForward("success");
    	
	}
}
 	
