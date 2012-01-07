package fr.dauphine.poleinfo3.client.test;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.dauphine.poleinfo3.ejb.Lang;
import fr.dauphine.poleinfo3.ejb.LanguageException;
import fr.dauphine.poleinfo3.ejb.MyHelloWorldRemote;

public class MyHelloWorldClient {
	public static void main(String[] args) {

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");
			ctx.addToEnvironment(InitialContext.PROVIDER_URL,
					"jnp://localhost:1099");
			//
			MyHelloWorldRemote myHelloWorld = (MyHelloWorldRemote) ctx
					.lookup("MyHelloWorldEJB/remote");
			myHelloWorld.create(args[0]);
			for (Lang lang : Lang.values()) {
				try {

					String hello = myHelloWorld.sayHello(lang);
					System.out.println("MyHelloWorld EJB says : " + hello);
				} catch (LanguageException e) {
					System.out.println(e.toString());
				}
			}
		myHelloWorld.remove();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
