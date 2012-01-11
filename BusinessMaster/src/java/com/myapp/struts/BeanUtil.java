/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import gestion_investisseurs.RemoteInvestisseurs;
//import gestion_events.EventsBeanRemote;
/**
 *
 * @author UTILISATEUR
 */
public class BeanUtil {
    private InitialContext context;
    //private RemoteInvestisseurs investisseurs;
    //private EventsBeanRemote events;
    private static BeanUtil bean = new BeanUtil();
    
    public BeanUtil(){
        //Connection();
    }
    
    /*private void Connection(){
        try {
            context = new InitialContext();
            context.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            context.addToEnvironment(InitialContext.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
            context.addToEnvironment(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
            
            investisseurs = (RemoteInvestisseurs)context.lookup("BeanInvestisseurs/remote");
            events = (EventsBeanRemote) context.lookup("EventsBean/remote");
        }catch(NamingException e){
            Logger.getLogger(BeanUtil.class.getName()).log(Level.SEVERE, null, e);
       
    }
    
   public static RemoteInvestisseurs getInvestisseurs(){
       return bean.investisseurs;
   }
   
   public static EventsBeanRemote getEvents(){
       return bean.events;
   }*/
}
