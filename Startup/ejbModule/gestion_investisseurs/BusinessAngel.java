package gestion_investisseurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="BusinessAngel")
@DiscriminatorValue("BA")

public class BusinessAngel extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	@Basic(optional=false)
	protected String nom;
	@Basic(optional=false)
	protected String mail;
	@Basic(optional=false)
	protected String mdp;
	private boolean isMandataire = false;
	@OneToMany(mappedBy="businessAngel")
	private List<Membre> clubAmis;
	
	public BusinessAngel(){}
	
	public BusinessAngel(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
		clubAmis = new ArrayList<Membre>();
	}
	
	public Membre devenirMembre (ClubAmi ca){
		return new Membre (ca, this);
	}
	
	public void quitterClub (ClubAmi ca){
		// TODO...
	}

	public long getIdBusinessAngel() {
		return idInvestisseur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isMandataire() {
		return isMandataire;
	}

	public void setMandataire(boolean isMandataire) {
		this.isMandataire = isMandataire;
	}

	public List<Membre> getClubAmis() {
		return clubAmis;
	}

	public void setClubAmis(List<Membre> clubAmis) {
		this.clubAmis = clubAmis;
	}
	
}
