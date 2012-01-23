package gestion_investisseurs;

import gestion_events.Startup;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name="ClubAmi")
public class ClubAmi implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idClub;
	@Column (nullable=false)
	private String nomClub;
	
	@OneToMany(mappedBy="clubAmi", cascade=CascadeType.ALL)
	List<Membre> businessAngels;
	
	@OneToOne
	@JoinColumn(referencedColumnName="idInvestisseur")
	private BusinessAngel mandataire;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName="idStartup")
	private Startup startup = null;
	
	public ClubAmi(){}
	
	public ClubAmi(String nom){
		this.nomClub = nom;
		businessAngels = new ArrayList<Membre>();
	}

	public long getIdClub() {
		return idClub;
	}

	public String getNomClub() {
		return nomClub;
	}

	public void setNomClub(String nomClub) {
		this.nomClub = nomClub;
	}
	
	public List<Membre> getMembres(){
		return businessAngels;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public BusinessAngel getMandataire() {
		return mandataire;
	}

	public void setMandataire(BusinessAngel mandataire) {
		this.mandataire = mandataire;
	}

	@Override
	public String toString() {
		return "ClubAmi [idClub=" + idClub + ", nomClub=" + nomClub
				+ ", mandataire=" + mandataire.getNom() + ", startup= " + startup + "]";
	}
}
