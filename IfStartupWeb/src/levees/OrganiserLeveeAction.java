package levees;

import com.vaannila.BeanUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class OrganiserLeveeAction extends org.apache.struts.action.Action{
    
        @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrganiserLeveeForm f = (OrganiserLeveeForm) form;
        double cible = Double.valueOf(f.getCible());
        try{
            BeanUtil.getInvestisseurs().organiserLeveeFonds(BeanUtil.getEvents().findStartupByName(f.getStartup()).get(0), BeanUtil.getInvestisseurs().findByEmail((String)request.getAttribute("login")), cible);
            return mapping.findForward("success-organiser_levee");
        }catch(Exception e){
            return mapping.findForward("error-organiser_levee");
        }
    }
}
