package gestion_investisseurs;

import gestion_events.Startup;

import javax.naming.*;

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
			System.out.println(remote.afficherText("Hello"));
			Fondateur f = new Fondateur ("Dupont", "dupont", "12345");
			Startup s = new Startup("Startup 1", "Informatique", 10.000, f);
			System.out.println(f);
			
			//System.out.println(remote.ajouterFondateur(f, s.getIdStartup()));		
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
}
