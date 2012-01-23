package fondateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class InvestirFondateurAction extends org.apache.struts.action.Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String s = ((InvestirFondateurForm)form).getStartup();
		String m = ((InvestirFondateurForm)form).getMontant();
		String login = (String)request.getSession().getAttribute("login");
		try{
			double mo = Double.parseDouble(m);
			BeanUtil.getEvents().participation(BeanUtil.getEvents().findStartupByName(s).get(0), BeanUtil.getInvestisseurs().findFondateurByEmail(login), mo);
			return mapping.findForward("success");
		}catch(Exception e){
			return mapping.findForward("error");
		}
	}
}
