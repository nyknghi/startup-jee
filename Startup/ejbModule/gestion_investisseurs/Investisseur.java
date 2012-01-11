package gestion_investisseurs;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Investisseur")
public class Investisseur extends AbstraitInvestisseur{
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName="idInvestisseur")
	private GroupeInvestisseurs groupe = null;
	
	@Column
	private boolean isLeader=false;
	
	public Investisseur(){}
	
	public Investisseur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
	}

	public GroupeInvestisseurs getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeInvestisseurs groupe) {
		this.groupe = groupe;
	}
	
	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	@Override
	public String toString() {
		return "Investisseur [idInvestisseur=" + idInvestisseur + ", nom=" + nom
				+ ", mail=" + mail + ", mdp=" + mdp + ", groupe=" + groupe.getNom() 
				+ ", isLeader=" + isLeader + "]"; 
	}
}
