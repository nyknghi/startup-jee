/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levees;

import com.vaannila.BeanUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author UTILISATEUR
 */
public class ParticiperAction extends org.apache.struts.action.Action{
 
    @Override
        public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ParticipationForm f = (ParticipationForm)form;
        String login = (String)request.getSession().getAttribute("login");
        try{
            if(request.getSession().getAttribute("User").equals("investisseur")){
                BeanUtil.getEvents().participation(BeanUtil.getEvents().findLeveeDeFonds(Long.parseLong(f.getIdLevee())), BeanUtil.getInvestisseurs().rechercherInvestisseurParMail(login), Double.valueOf(f.getMontant()));
                return mapping.findForward("success-inv");
            }else{
                BeanUtil.getEvents().participation(BeanUtil.getEvents().findLeveeDeFonds(Long.parseLong(f.getIdLevee())), BeanUtil.getInvestisseurs().rechercherBAParMail(login), Double.valueOf(f.getMontant()));
                return mapping.findForward("success-ba");
            }
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
}
