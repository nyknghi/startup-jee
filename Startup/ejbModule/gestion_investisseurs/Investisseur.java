package gestion_investisseurs;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Investisseur")
@DiscriminatorValue("INVESTISSEUR")
public class Investisseur extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupeId")
	private GroupeInvestisseurs groupe;
	private boolean isLeader;
	
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
}
