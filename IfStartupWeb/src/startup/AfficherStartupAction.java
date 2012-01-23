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

public class AfficherStartupAction extends org.apache.struts.action.Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		List<Startup> startups = null;

		EventsBeanRemote remoteEvents = BeanUtil.getEvents();		
		startups = remoteEvents.findAllStartup();
		request.setAttribute("startups", startups);
		if(request.getSession().getAttribute("User").equals("root")){
			return mapping.findForward("success-root");
		}else{
			return mapping.findForward("success");
		}
	}
}
