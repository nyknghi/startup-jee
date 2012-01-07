package startup.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Fondateur")
@DiscriminatorValue("FONDATEUR")
@NamedQuery(name="findFondateurByName", query="SELECT f FROM Fondateur as f WHERE f.nom = :nom")
public class Fondateur extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="startup_id")
	private Startup startup;
	
	public Fondateur(){}
	
	public Fondateur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
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

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}
}
