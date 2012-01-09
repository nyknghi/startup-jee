package gestion_investisseurs;

import gestion_events.LeveeDeFonds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="BusinessAngel")
@DiscriminatorValue("BA")

public class BusinessAngel extends AbstraitInvestisseur{
	private static final long serialVersionUID = 1L;
	
	private boolean isMandataire = false;
	@OneToMany(mappedBy="businessAngel")
	private List<Membre> clubAmis;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="organisateur")
	private Set<LeveeDeFonds> leveeDeFonds;
	
	public BusinessAngel(){}
	
	public BusinessAngel(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
		clubAmis = new ArrayList<Membre>();
		leveeDeFonds = new HashSet<LeveeDeFonds>();
	}
	
	public Membre devenirMembre (ClubAmi ca){
		return new Membre (ca, this);
	}
	
	public void quitterClub (ClubAmi ca){
		// TODO...
	}

	public Long organisateurId(){
		return getIdInvestisseur();
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

	public Set<LeveeDeFonds> getLeveeDeFonds() {
		return leveeDeFonds;
	}

	public void setLeveeDeFonds(Set<LeveeDeFonds> leveeDeFonds) {
		this.leveeDeFonds = leveeDeFonds;
	}	
}
