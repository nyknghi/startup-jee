/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package investisseur;

import com.vaannila.BeanUtil;
import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Fondateur;
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
public class CreerInvestisseurAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CreerInvestisseurForm f = (CreerInvestisseurForm) form;
        ArrayList<InvestisseurBean> list = new ArrayList<InvestisseurBean>();
        try{
            if(f.getType().equals("Fondateur")){
                BeanUtil.getInvestisseurs().creerFondateur(f.getNom(), f.getEmail(), f.getPassword());
            }else if(f.getType().equals("BA")){
                BeanUtil.getInvestisseurs().creerBA(f.getNom(), f.getEmail(), f.getPassword());
            }else{
                BeanUtil.getInvestisseurs().creerInvestisseur(f.getNom(), f.getEmail(), f.getPassword());
            }
            ArrayList<AbstraitInvestisseur> fo = (ArrayList<AbstraitInvestisseur>) BeanUtil.getInvestisseurs().findAllInvestisseurs();
            Iterator it = fo.iterator();
            while(it.hasNext()){
                Fondateur fond = (Fondateur)it.next();
                list.add(new InvestisseurBean(fond.getNom(), fond.getMail(), fond.getMdp()));
            }
            f.setList(list);
            return mapping.findForward("success-creerInvestisseur");
        }catch(Exception e){
            return mapping.findForward("errors-creerInvestisseur"); 
        }
    }
}
