/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaannila;

import gestion_events.EventsBeanRemote;
import gestion_investisseurs.RemoteInvestisseurs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author UTILISATEUR
 */
public class BeanUtil {
    private RemoteInvestisseurs investisseurs;
    private EventsBeanRemote events;
    private InitialContext ctx;
    private static BeanUtil bean = new BeanUtil();
    
    private BeanUtil(){
        connection();
    }
    
    private void connection(){
        try {
            ctx = new InitialContext();
            ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
            ctx.addToEnvironment(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
            
            investisseurs = (RemoteInvestisseurs)ctx.lookup("BeanInvestisseurs/remote");
            events = (EventsBeanRemote)ctx.lookup("EventsBean/remote");
        } catch (NamingException ex) {
            Logger.getLogger(BeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static RemoteInvestisseurs getInvestisseurs(){
        return bean.investisseurs;
    }
    
    public static EventsBeanRemote getEvents(){
        return bean.events;
    }
}
