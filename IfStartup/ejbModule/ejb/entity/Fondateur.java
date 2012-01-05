package ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(schema="ifstartup", name="fondateur")
public class Fondateur extends AbstraitInvestisseur implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFondateur;
	@Basic(optional=false)
	private String nom;
	@Basic(optional=false)
	private String mail;
	@Basic(optional=false)
	private String mdp;
	
	public Fondateur(){}
	
	public Fondateur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	@Override
	public Participation investir(Startup st, float montant) {
		return null;
	}

	@Override
	public Inscription inscrireLeveeDeFonds(LeveeDeFonds le) {
		return null;
	}

	public int getIdFondateur() {
		return idFondateur;
	}

	public void setIdFondateur(int idFondateur) {
		this.idFondateur = idFondateur;
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
