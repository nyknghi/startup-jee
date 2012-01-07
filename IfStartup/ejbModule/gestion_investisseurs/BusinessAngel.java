package gestion_investisseurs;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(schema="IFStartupBD", name="BusinessAngel")
@DiscriminatorValue("BA")

public class BusinessAngel extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;

	public BusinessAngel(){}
	
	public ClubAmi monterClub (String nom){
		return new ClubAmi (nom);
	}

	public Membre devenirMembre (ClubAmi ca){
		return new Membre (ca, this);
	}
	
	public void quitterClub (ClubAmi ca){
		// TODO...
	}

	public int getIdFondateur() {
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
	
}
