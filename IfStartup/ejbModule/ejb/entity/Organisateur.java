package ejb.entity;

import java.util.Date;

public interface Organisateur {
	public void organiserLeveeFonds(Date date, float cible);
	public void modifierEtape();
	public void annulerLevee();
}
