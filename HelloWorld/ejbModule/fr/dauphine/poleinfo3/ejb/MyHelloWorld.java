package fr.dauphine.poleinfo3.ejb;

public interface MyHelloWorld {
	public void create(String myName);
	public void remove();
	public String sayHello(Lang lang) throws LanguageException;
}