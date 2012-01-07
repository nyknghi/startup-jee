package fr.dauphine.poleinfo3.ejb;

import fr.dauphine.poleinfo3.ejb.Lang;

public class LanguageException extends Exception {
	public LanguageException(Lang lang) {
		super("Unknown language "+lang.langName());
	}
	private static final long serialVersionUID = 2072702863798159902L;
}