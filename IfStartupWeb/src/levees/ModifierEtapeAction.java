package levees;

import gestion_events.LeveeDeFonds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class ModifierEtapeAction extends org.apache.struts.action.Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EtapeLeveeForm f = (EtapeLeveeForm)form;
		try{
			LeveeDeFonds l = BeanUtil.getEvents().findLeveeDeFonds(Long.parseLong(f.getIdLevee()));
			BeanUtil.getInvestisseurs().modifierEtape(l);
			if(request.getSession().getAttribute("User").equals("BA")){
				return mapping.findForward("success-BA");
			}else{
				return mapping.findForward("success-fondateur");
			}
		}catch(Exception e){
			return mapping.findForward("error");
		}
	}
}
