package startup;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.RemoteInvestisseurs;

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

public class AfficherStartupAction extends org.apache.struts.action.Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		List<Startup> startups = null;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();		
		startups = remoteEvents.findAllStartup();
		request.setAttribute("startups", startups);
		return mapping.findForward("success");
	}
}
