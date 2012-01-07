package startup.client.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import startup.ejb.entity.Fondateur;
import startup.ejb.session.InvestisseursRemote;


public class Main {
	public static void main(String[] args) {
		Context ctx;
		try {
			Properties props = new Properties();
			try {
				props.load(new FileInputStream("./jndi.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		//System.out.println(props.toString());
		
		ctx = new InitialContext();
		/*ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		ctx.addToEnvironment(InitialContext.PROVIDER_URL,
				"jnp://localhost:1099");
		*/    
		InvestisseursRemote g_inv = (InvestisseursRemote) ctx.lookup("InvestisseursBean/remote");
		 
		Fondateur f = new Fondateur("Dupont", "dupont@gmail.com", "123456");
		g_inv.ajouterFondateur(f);
    	
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}

