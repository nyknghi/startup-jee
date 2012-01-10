package testUnit;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.*;

public class Test {
	public static void main (String[] args){
		try{
			Context ctx = new InitialContext();
			ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");
			ctx.addToEnvironment(InitialContext.PROVIDER_URL,
					"jnp://localhost:1099");
			
			RemoteInvestisseurs remoteInv = (RemoteInvestisseurs) ctx.lookup("BeanInvestisseurs/remote");
			EventsBeanRemote remoteEvents = (EventsBeanRemote) ctx.lookup("EventsBean/remote");
			
			System.out.println(remoteInv.afficherText("Hello"));
			
			Fondateur f = remoteInv.creerFondateur("Dupont", "dupont@gmail.com", "12345");
			Startup s = remoteEvents.startup("MonStartup", "Informatique", f);
			f.setStartup(s);
			
			System.out.println(s);
			System.out.println(f);
			
			//remote.closeEM();
			
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
}
