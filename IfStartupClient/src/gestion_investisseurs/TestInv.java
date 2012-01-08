package gestion_investisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestInv {
	public static void main (String[] args){
		try{
			Context ctx = new InitialContext();
			ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");
			ctx.addToEnvironment(InitialContext.PROVIDER_URL,
					"jnp://localhost:1099");
			
			RemoteInvestisseurs remote = (RemoteInvestisseurs) ctx.lookup("BeanInvestisseurs/remote");
			System.out.println(remote.afficherText("Hello Nghi"));
			
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
}
