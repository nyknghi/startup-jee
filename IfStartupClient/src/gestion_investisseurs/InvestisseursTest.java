package gestion_investisseurs;

import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class InvestisseursTest {
	public static void main(String[] args) {
		Context ctx;
		try {
		/*	Properties props = new Properties();
			try {
				props.load(new FileInputStream("./jndi.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}*/  
			
		ctx = new InitialContext();		 
		ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		ctx.addToEnvironment(InitialContext.PROVIDER_URL,
				"jnp://localhost:1099");
		
		RemoteInvestisseurs g_inv = (RemoteInvestisseurs) ctx.lookup("InvestisseursBean/remote");
		g_inv.afficherText("Hello Nghi");
		
		//Fondateur f = new Fondateur("Dupont", "dupont@gmail.com", "123456");
	
	//	g_inv.ajouterFondateur(f);
    	
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}

