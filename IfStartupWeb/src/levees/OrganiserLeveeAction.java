package levees;

import com.vaannila.BeanUtil;

import gestion_events.Startup;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Membre;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class OrganiserLeveeAction extends org.apache.struts.actions.DispatchAction{
    
    public ActionForward load(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        LeveeForm f = (LeveeForm)form;
        ArrayList<StartupBean> list = new ArrayList<StartupBean>();
        String login = (String)request.getSession().getAttribute("login");
        String user = (String)request.getSession().getAttribute("User");
        System.out.println(user+" "+login);
        try{
            if(user.equals("fondateur")){
            	Startup s = BeanUtil.getInvestisseurs().findFondateurByEmail(login).getStartup();
            	System.out.println(Long.toString(s.getIdStartup())+"||"+s.getNomStartup());
                list.add(new StartupBean(Long.toString(s.getIdStartup()), s.getNomStartup()));
            }else if(user.equals("BA")){
                ArrayList<ClubAmi> clubs = (ArrayList<ClubAmi>)BeanUtil.getInvestisseurs().findAllClub();
                ArrayList<Membre> membres = (ArrayList<Membre>)BeanUtil.getInvestisseurs().findAllMembers();
                BusinessAngel a = BeanUtil.getInvestisseurs().rechercherBAParMail(login);
                ArrayList<Membre> temp = new ArrayList<Membre>();
                for(Membre m: membres){
                	if(m.getIdBA()==a.getIdInvestisseur() && m.isMandataire()){
                		temp.add(m);
                		System.out.println(Long.toString(m.getIdBA())+"||"+m.isMandataire());
                	}
                }
                for(Membre me: temp){
                    for(ClubAmi c: clubs){
                        if(c.getIdClub()==me.getIdClub()){
                            list.add(new StartupBean(Long.toString(c.getStartup().getIdStartup()), c.getStartup().getNomStartup()));
                            System.out.println(Long.toString(c.getStartup().getIdStartup())+"||"+c.getStartup().getNomStartup());
                        }
                    }
                }
            }
        	f.setStartupList(list);
        	return mapping.findForward("success");
        }catch(Exception e){
        	return mapping.findForward("error");
        }
    }
    
    public ActionForward Creer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LeveeForm f = (LeveeForm)form;
        String login = (String)request.getSession().getAttribute("login");
        System.out.println(login);
        try{
            if(request.getSession().getAttribute("User").equals("fondateur")){
                BeanUtil.getInvestisseurs().organiserLeveeFonds(BeanUtil.getInvestisseurs().findFondateurByEmail(login).getStartup(), BeanUtil.getInvestisseurs().findFondateurByEmail(login), Double.valueOf(f.getCible()));
                return mapping.findForward("success-fondateur");
            }else{
            	Startup s = BeanUtil.getEvents().findStartupById(Long.parseLong(f.getStartup()));
            	BusinessAngel ba = BeanUtil.getInvestisseurs().rechercherBAParMail(login);          
            	BeanUtil.getInvestisseurs().organiserLeveeFonds(s, ba, Double.valueOf(f.getCible()));
	            return mapping.findForward("success-BA");
            }
        }catch(Exception e){
            return mapping.findForward("error");
        }
    }
}
