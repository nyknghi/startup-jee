/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levees;

import com.vaannila.BeanUtil;
import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.Investisseur;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author UTILISATEUR
 */
public class InvestirAction extends org.apache.struts.action.Action{
    
    public ActionForward load(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList ins_levees = new ArrayList();
        String login = (String)request.getSession().getAttribute("login");
        try{
            ArrayList<LeveeDeFonds> levees = (ArrayList<LeveeDeFonds>)BeanUtil.getEvents().findAllLevees();
            for(LeveeDeFonds fo: levees){
                ArrayList<Inscription> ins = (ArrayList<Inscription>)fo.getInscriptions();
                for(Inscription i: ins){
                    if(request.getSession().getAttribute("User").equals("investisseur")){
                        if(((Investisseur)i.getInvestisseur()).getMail().equals(login)){
                            ins_levees.add(new LeveeBean(Long.toString(fo.getIdLevee()), fo.getDate_levee().toString(), fo.getStartup().getNomStartup()));
                        }
                    }else{
                        if(((BusinessAngel)i.getInvestisseur()).getMail().equals(login)){
                            ins_levees.add(new LeveeBean(Long.toString(fo.getIdLevee()), fo.getDate_levee().toString(), fo.getStartup().getNomStartup()));
                        }
                    }
                }
            }
            request.setAttribute("inscriptions_levees",ins_levees);
            return mapping.findForward("success-load");
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InscriptionForm f = (InscriptionForm)form;
        request.setAttribute("investir_levee", f.getIdLevee());
        return mapping.findForward("success");
    }
    
}
