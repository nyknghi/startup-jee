package utilite;

import package gestion_microcredit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BeanUtil {
	 
	    private MicrocreditBeanRemote gestionnaire;
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
	            
	            gestionnaire = (MicrocreditBeanRemote)ctx.lookup("MicrocreditBean/remote");
	            
	        } catch (NamingException ex) {
	            Logger.getLogger(BeanUtil.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    public static MicrocreditBeanRemote getGestionnaire(){
	        return bean.gestionnaire;
	    }
	    
	    /*public static MicrocreditBeanRemote getEvents(){
	        return bean.events;
	    }*/

}
