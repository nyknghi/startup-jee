package login;

import com.vaannila.BeanUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class LoginAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        HttpSession se = request.getSession(true);
        if (loginForm.getEmail().equals("root") && loginForm.getPassword().equals("admin")){
            se.setAttribute("login", "root");
            se.setAttribute("password", "admin");
            se.setAttribute("User", "root");
            return mapping.findForward("success");
        }else if (BeanUtil.getInvestisseurs().listeAccountsInvestisseurs().containsKey(loginForm.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsInvestisseurs().containsValue(loginForm.getPassword())){
            se.setAttribute("login", loginForm.getEmail());
            se.setAttribute("password", loginForm.getPassword());
            se.setAttribute("User", "investisseur");
            return mapping.findForward("investisseur");
        }else if(BeanUtil.getInvestisseurs().listeAccountsFondateurs().containsKey(loginForm.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsFondateurs().containsValue(loginForm.getPassword())){
            se.setAttribute("login", loginForm.getEmail());
            se.setAttribute("password", loginForm.getPassword());
            se.setAttribute("User", "fondateur");
            return mapping.findForward("fondateur");
        }else if(BeanUtil.getInvestisseurs().listeAccountsBA().containsKey(loginForm.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsBA().containsValue(loginForm.getPassword())){
            se.setAttribute("login", loginForm.getEmail());
            se.setAttribute("password", loginForm.getPassword());
            se.setAttribute("User", "BA");
            return mapping.findForward("ba");
        }
        return mapping.findForward("failure");
    }
}
