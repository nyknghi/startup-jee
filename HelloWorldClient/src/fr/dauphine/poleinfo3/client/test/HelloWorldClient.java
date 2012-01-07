package fr.dauphine.poleinfo3.client.test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import fr.dauphine.poleinfo3.ejb.HelloWorldRemote;
import fr.dauphine.poleinfo3.ejb.Lang;
import fr.dauphine.poleinfo3.ejb.LanguageException;

public class HelloWorldClient {
	public static void main(String[] args) {
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
		    ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		    ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		    ctx.addToEnvironment(InitialContext.PROVIDER_URL,"jnp://localhost:1099");
		    //
		    HelloWorldRemote helloWorld = (HelloWorldRemote) ctx.lookup("HelloWorldEJB/remote");
		    for (Lang lang : Lang.values()) {
				try {
					String hello = helloWorld.sayHello(args[0],lang);
					System.out.println("HelloWorld EJB says : "+hello);
				} catch (LanguageException e) {
					System.out.println(e.toString());
				}
				
		    }
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
