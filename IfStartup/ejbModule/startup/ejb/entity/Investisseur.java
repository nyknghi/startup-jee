package startup.ejb.entity;

public class Investisseur extends AbstraitInvestisseur {

	@Override
	public Participation investir(Startup st, float montant) {
		return null;
	}

	@Override
	public Inscription inscrireLeveeDeFonds(LeveeDeFonds le) {
		return null;
	}

}
