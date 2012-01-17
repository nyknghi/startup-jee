package levees;

import com.vaannila.BeanUtil;
import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Membre;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.LabelValueBean;

public class OrganiserLeveeAction extends org.apache.struts.action.Action{
    
    public ActionForward load(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        LeveeForm f = (LeveeForm)form;
        ArrayList list = new ArrayList();
        String login = (String)request.getSession().getAttribute("login");
        if(request.getSession().getAttribute("User").equals("fondateur")){
            list.add(new LabelValueBean(BeanUtil.getInvestisseurs().findFondateurByEmail(login).getStartup().getNomStartup(), Long.toString(BeanUtil.getInvestisseurs().findFondateurByEmail(login).getStartup().getIdStartup())));
        }else{
            ArrayList<ClubAmi> clubs = (ArrayList<ClubAmi>)BeanUtil.getInvestisseurs().findAllClub();
            for(ClubAmi c: clubs){
                ArrayList<Membre> membres = (ArrayList<Membre>)c.getMembres();
                for(Membre m: membres){
                    if(m.isMandataire() && m.getBusinessAngel().getMail().equals(login)){
                        list.add(new LabelValueBean(c.getStartup().getNomStartup(), Long.toString(c.getStartup().getIdStartup())));
                        break;
                    }
                }
            }
        }
        f.setStartupList(list);
        return mapping.findForward("success");
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LeveeForm f = (LeveeForm)form;
        String login = (String)request.getSession().getAttribute("login");
        try{
            if(request.getSession().getAttribute("User").equals("fondateur")){
                BeanUtil.getInvestisseurs().organiserLeveeFonds(BeanUtil.getInvestisseurs().findFondateurByEmail(login).getStartup(), BeanUtil.getInvestisseurs().findFondateurByEmail(login), Double.valueOf(f.getCible()));
                return mapping.findForward("success-fondateur");
            }else{
                BeanUtil.getInvestisseurs().organiserLeveeFonds(BeanUtil.getInvestisseurs().findFondateurByEmail(login).getStartup(), BeanUtil.getInvestisseurs().rechercherBAParMail(login), Double.valueOf(f.getCible()));
                return mapping.findForward("success-BA");
            }
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
}
