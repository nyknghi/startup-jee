package gestion_investisseurs;

import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_events.Participation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstraitInvestisseur implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long idInvestisseur;
	@Column(nullable=false)
	protected String nom;
	@Column
	protected String mail;
	@Column
	protected String mdp;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="investisseur")
	private Set<Participation> participations = new HashSet<Participation>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="investisseur")
	private Set<Inscription> inscriptions = new HashSet<Inscription>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="organisateur")
	private Set<LeveeDeFonds> leveeDeFonds = new HashSet<LeveeDeFonds>();
	
	public long getIdInvestisseur() {
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

	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public Set<LeveeDeFonds> getLeveeDeFonds() {
		return leveeDeFonds;
	}

	public void setLeveeDeFonds(Set<LeveeDeFonds> leveeDeFonds) {
		this.leveeDeFonds = leveeDeFonds;
	}

    public void addParticipation(Participation p) {
        participations.add(p);
    }

	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
}
