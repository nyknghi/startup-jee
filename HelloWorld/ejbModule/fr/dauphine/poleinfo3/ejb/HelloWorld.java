package fr.dauphine.poleinfo3.ejb;

public interface HelloWorld {
	public String sayHello(String who, Lang lang) throws LanguageException;
}