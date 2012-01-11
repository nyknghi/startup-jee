/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.myapp.struts.BeanUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author UTILISATEUR
 */
public class LoginAction extends org.apache.struts.action.Action {

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
        /*java.io.BufferedReader lec = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream("./Admin.txt")));
        String line="";
        java.util.ArrayList<String> prop = new java.util.ArrayList<String>();
        while((line=lec.readLine())!=null){
            prop.add(line);
        }
        LoginForm log = (LoginForm)form;
        if((!prop.get(0).equals("")) || (log.getEmail().equals(prop.get(0)))){
            if((!prop.get(1).equals("")) || (log.getPassword().equals(prop.get(1)))){
                return mapping.findForward("admin");
            }
        }else if(BeanUtil.getInvestisseurs().listeAccountsFondateurs().containsKey(log.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsFondateurs().containsValue(log.getPassword())){
            return mapping.findForward("fondateur");
        }else if(BeanUtil.getInvestisseurs().listeAccountsInvestisseurs().containsKey(log.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsInvestisseurs().containsValue(log.getPassword())){
            return mapping.findForward("investisseur");
        }else if(BeanUtil.getInvestisseurs().listeAccountsBA().containsKey(log.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsBA().containsValue(log.getPassword())){
            return mapping.findForward("ba");
        }*/
        return mapping.findForward("success");
    }
}
