package startup.ejb.entity;

public abstract class AbstraitInvestisseur {

	public abstract Participation investir (Startup st, float montant);
	public abstract Inscription inscrireLeveeDeFonds (LeveeDeFonds le);
	
}
