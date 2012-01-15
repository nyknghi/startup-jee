/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package investisseur;

import com.vaannila.BeanUtil;
import gestion_investisseurs.AbstraitInvestisseur;
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
public class AllInvestisseursAction extends org.apache.struts.action.Action {

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
        ArrayList<AbstraitInvestisseur> investisseurs = (ArrayList<AbstraitInvestisseur>) BeanUtil.getInvestisseurs().findAllInvestisseurs();
    
        request.setAttribute("investisseurs", investisseurs);
        return mapping.findForward("success");
    }
}
