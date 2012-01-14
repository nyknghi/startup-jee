package investisseur;

@SuppressWarnings("serial")
public class CreerClubAmiForm extends org.apache.struts.action.ActionForm{
	private String nomClub;

	public String getNomClub() {
		return nomClub;
	}

	public void setNomClub(String nomClub) {
		this.nomClub = nomClub;
	}
}
