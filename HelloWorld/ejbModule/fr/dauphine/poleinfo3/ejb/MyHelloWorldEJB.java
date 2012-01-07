package fr.dauphine.poleinfo3.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class MyHelloWorldEJB implements MyHelloWorldLocal, MyHelloWorldRemote {
	private String name;
	public void create(String name) {
		System.out.println("Calling create ("+name+")");
		this.name = name;
	}
	public String sayHello(Lang lang) throws LanguageException {
		System.out.println("Calling sayHello (" + lang.langName()+ ")");
		String hello = null;
		switch (lang) {
		case FRENCH:
			hello = "Bonjour " + name;
			break;
		case ENGLISH:
			hello = "Hello " + name;
			break;
		case SPANISH:
			hello = "Hola " + name;
			break;
		default:
			throw new LanguageException(lang);
		}
		return hello;
	}
	
	@Remove
	public void remove() {
		System.out.println("calling remove method");
		name = null;
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