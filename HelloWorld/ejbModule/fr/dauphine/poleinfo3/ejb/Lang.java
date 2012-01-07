package fr.dauphine.poleinfo3.ejb;

public enum Lang {
	FRENCH("French"), ENGLISH("English"), SPANISH("Spanish"), DANISH("Danish");
	private String lang;

	private Lang(String lang) {
		this.lang = lang;
	}

	public String langName() {
		return lang;
	}
}