/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inscription;

import com.vaannila.BeanUtil;
import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.Investisseur;
import java.util.ArrayList;
import java.util.Iterator;

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
        ArrayList<InscriptionBean> list_levees = new ArrayList<InscriptionBean>();
        String login = (String)request.getSession().getAttribute("login");
        AbstraitInvestisseur inv;
        try{
        	if(request.getSession().getAttribute("User").equals("BA")){
            	inv = (BusinessAngel)BeanUtil.getInvestisseurs().rechercherBAParMail(login);
            }else{
            	inv = (Investisseur)BeanUtil.getInvestisseurs().rechercherInvestisseurParMail(login);
            }
            ArrayList<LeveeDeFonds> levees = (ArrayList<LeveeDeFonds>)BeanUtil.getEvents().findAllLevees();
            boolean flag;
            for(LeveeDeFonds l: levees){
            	flag = false;
            	for(Inscription i: l.getInscriptions()){
            		if(i.getInvestisseur().getIdInvestisseur()==inv.getIdInvestisseur()){
            			flag = true;
            			break;
            		}
            	}
            	if(flag==false){
            		list_levees.add(new InscriptionBean(Long.toString(l.getIdLevee()), l.getDate_levee().toString(), l.getStartup().getNomStartup(), l.getEtape().toString(),l.getMontantCible(), BeanUtil.getEvents().totalParticipations(l)));
            	}
            }
            request.setAttribute("list_levees",list_levees);
            return mapping.findForward("success");
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
        
    public ActionForward Participer(ActionMapping mapping, ActionForm form,
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
