package fr.dauphine.poleinfo3.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless
public class HelloWorldEJB implements HelloWorldLocal, HelloWorldRemote {
	public String sayHello(String who, Lang lang) throws LanguageException {
		System.out.println("Calling sayHello (" + who + "," + lang.langName()
				+ ")");
		String hello = null;
		switch (lang) {
		case FRENCH:
			hello = "Bonjour " + who;
			break;
		case ENGLISH:
			hello = "Hello " + who;
			break;
		case SPANISH:
			hello = "Hola " + who;
			break;
		default:
			throw new LanguageException(lang);
		}
		return hello; 
	}

	@PostConstruct
	public void init() {
		System.out.println("calling init method");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println("calling cleanup method");
	}
}