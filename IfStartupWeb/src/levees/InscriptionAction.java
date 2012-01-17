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
public class InscriptionAction extends org.apache.struts.actions.DispatchAction{

    private static final String SUCCESS = "success";

        public ActionForward load(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList list_levees = new ArrayList();
        String login = (String)request.getSession().getAttribute("login");
        try{
            ArrayList<LeveeDeFonds> levees = (ArrayList<LeveeDeFonds>)BeanUtil.getEvents().findAllLevees();
            for(LeveeDeFonds l:levees){
                ArrayList<Inscription> inscriptions = (ArrayList<Inscription>)l.getInscriptions();
                for(Inscription i: inscriptions){
                    if(request.getSession().getAttribute("User").equals("BA")){
                        if(!((BusinessAngel)i.getInvestisseur()).getMail().equals(login)){
                            list_levees.add(new LeveeBean(Long.toString(l.getIdLevee()), l.getDate_levee().toString(), l.getStartup().getNomStartup()));
                        }
                    }else if(request.getSession().getAttribute("User").equals("BA")){
                        if(!((Investisseur)i.getInvestisseur()).getMail().equals(login)){
                            list_levees.add(new LeveeBean(Long.toString(l.getIdLevee()), l.getDate_levee().toString(), l.getStartup().getNomStartup()));
                        }
                    }
                }
            }
            request.setAttribute("list_levees",list_levees);
            return mapping.findForward("success-all");
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
        
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InscriptionForm f = (InscriptionForm)form;
        String login = (String)request.getSession().getAttribute("login");
        try{
            if(request.getSession().getAttribute("User").equals("investisseur")){
                Investisseur i = BeanUtil.getInvestisseurs().rechercherInvestisseurParMail(login);
                if(i.isLeader()){
                    BeanUtil.getInvestisseurs().inscrireLevee(i, BeanUtil.getEvents().findLeveeDeFonds(Long.parseLong(f.getIdLevee())));
                    return mapping.findForward("success-inv");
                }
                return mapping.findForward("error");
            }else{
                BeanUtil.getInvestisseurs().inscrireLevee(BeanUtil.getInvestisseurs().rechercherBAParMail(login), BeanUtil.getEvents().findLeveeDeFonds(Long.parseLong(f.getIdLevee())));
                return mapping.findForward("success-BA");
            }
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
}
