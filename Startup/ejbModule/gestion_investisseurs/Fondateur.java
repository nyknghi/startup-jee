package gestion_investisseurs;

import gestion_events.Startup;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Fondateur")
@NamedQuery(name="findFondateurByName", query="SELECT f FROM Fondateur as f WHERE f.nom = :nom")
public class Fondateur extends AbstraitInvestisseur implements Serializable{
	@Basic(optional=false)
	protected String nom;
	@Basic(optional=false)
	protected String mail;
	@Basic(optional=false)
	protected String mdp;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="startup_id", referencedColumnName="idStartup")
	private Startup startup;
	private boolean isMandataire=false;
	
	public Fondateur(){}
	
	public Fondateur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public boolean isMandataire() {
		return isMandataire;
	}

	public void setMandataire(boolean isMandataire) {
		this.isMandataire = isMandataire;
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
	@Override
	public String toString() {
		return "Fondateur [nom=" + nom + ", startup=" + startup.getNomStartup() + ", isMandataire="
				+ isMandataire + ", mail=" + mail + ", mdp=" + mdp +"]";
	}
}
