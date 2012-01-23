package investir;

import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.Investisseur;
import inscription.InscriptionBean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vaannila.BeanUtil;

public class ChoisirLeveeAction extends org.apache.struts.action.Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
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
            	if(flag==true){
            		list_levees.add(new InscriptionBean(Long.toString(l.getIdLevee()), l.getDate_levee().toString(), l.getStartup().getNomStartup()));
            	}
            }
            request.setAttribute("levees_inscr",list_levees);
            return mapping.findForward("success");
        }catch(Exception e){
            return mapping.findForward("error");
        }
	}
}
