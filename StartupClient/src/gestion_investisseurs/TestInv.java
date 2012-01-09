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
						
			//Startup s = new Startup("Startup 1", "Informatique", 10.000, f);
			//System.out.println(f);
			
			Fondateur f = new Fondateur("Dupont", "dupont@gmail.com", "12345");
			remote.ajouterFondateur(f);
						
			remote.creerStartup("MonStartup", "Informatique", 20.000, f);
			Startup s = remote.rechercherStartupParId(2);
			//remote.ajouterFondateurStartup(f, s);
			
			//System.out.println(f);
			
			//remote.closeEM();
			
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
}
