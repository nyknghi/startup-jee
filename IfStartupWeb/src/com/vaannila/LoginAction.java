package com.vaannila;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class LoginAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        if (loginForm.getEmail().equals("root") && loginForm.getPassword().equals("admin")){
            return mapping.findForward("success");
        }else if (BeanUtil.getInvestisseurs().listeAccountsInvestisseurs().containsKey(loginForm.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsInvestisseurs().containsValue(loginForm.getPassword())){
            return mapping.findForward("investisseur");
        }else if(BeanUtil.getInvestisseurs().listeAccountsFondateurs().containsKey(loginForm.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsFondateurs().containsValue(loginForm.getPassword())){
            return mapping.findForward("fondateur");
        }else if(BeanUtil.getInvestisseurs().listeAccountsBA().containsKey(loginForm.getEmail()) && BeanUtil.getInvestisseurs().listeAccountsBA().containsValue(loginForm.getPassword())){
            return mapping.findForward("ba");
        }
        return mapping.findForward("failure");
    }
}