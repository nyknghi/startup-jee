package ejb.entite;

public abstract class AbstraitInvestisseur {
	protected int idInvestisseur;
	protected String nom;
	protected String login;
	protected String mdp;
	
	public abstract Participation investir (Startup st, float montant);
	public abstract Inscription inscrireLeveeDeFonds (LeveeDeFonds le);
}
