package levees;

public class StartupBean {

	private String idStartup;
	private String nom;
	
	public StartupBean(String id, String n){
		idStartup = id;
		nom = n;
	}

	public String getIdStartup() {
		return idStartup;
	}

	public void setIdStartup(String idStartup) {
		this.idStartup = idStartup;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
