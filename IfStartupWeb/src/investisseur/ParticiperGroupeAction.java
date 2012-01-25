package investisseur;

import java.util.ArrayList;
import java.util.List;

import gestion_events.EventsBeanRemote;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.GroupeInvestisseurs;
import gestion_investisseurs.Investisseur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.vaannila.BeanUtil;

import util.Couple;

public class ParticiperGroupeAction extends DispatchAction {
    public ActionForward populate(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	List<GroupeInvestisseurs> groupes = null;
//		ArrayList groupeList = new ArrayList();
//		ParticiperGroupeForm participerGroupeForm = (ParticiperGroupeForm) form;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();    	
    	
		groupes = remoteInv.findAllGroupe();
		request.setAttribute("groupes", groupes);
//		GroupeInvestisseurs[] aGroupes = groupes.toArray(new GroupeInvestisseurs[groupes.size()]);
//		for (int i=0; i<groupes.size(); i++){
//			int t = i + 1;
//			String si = "" + t;
//			groupeList.add(new LabelValueBean(aGroupes[i].getNom(), si));
//		}
//		participerGroupeForm.setGroupeList(groupeList);    	
    	return mapping.findForward("success");
    }
    
    public ActionForward participer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	ParticiperGroupeForm participerGroupeForm = (ParticiperGroupeForm) form;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();    
		
		String sId = request.getParameter("id");
		GroupeInvestisseurs groupe = BeanUtil.getInvestisseurs().rechercherGroupeParId(Long.parseLong(sId));
		Investisseur inv = BeanUtil.getInvestisseurs().rechercherInvestisseurParMail((String)request.getSession().getAttribute("login"));
		Boolean isLeader = participerGroupeForm.getCheckboxValue();
		Couple<GroupeInvestisseurs, Investisseur> res = remoteInv.adhererGroupe(groupe, inv, false);
    	return mapping.findForward("return");
    }
    
    public ActionForward quitter(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	ParticiperGroupeForm participerGroupeForm = (ParticiperGroupeForm) form;
		RemoteInvestisseurs remoteInv = BeanUtil.getInvestisseurs();
		EventsBeanRemote remoteEvents = BeanUtil.getEvents();    
		
		String sId = request.getParameter("id");
		GroupeInvestisseurs groupe = BeanUtil.getInvestisseurs().rechercherGroupeParId(Long.parseLong(sId));
		Investisseur inv = BeanUtil.getInvestisseurs().rechercherInvestisseurParMail((String)request.getSession().getAttribute("login"));
		Boolean isLeader = participerGroupeForm.getCheckboxValue();
		Couple<GroupeInvestisseurs, Investisseur> res = remoteInv.quitterGroupe(groupe, inv);
    	return mapping.findForward("return");
    }    
}
