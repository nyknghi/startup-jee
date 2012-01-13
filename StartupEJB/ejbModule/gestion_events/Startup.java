package gestion_events;

import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Fondateur;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Startup")
public class Startup implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idStartup;
        
	@Basic(optional=false)
	private String nomStartup;
	
    @Column (nullable=false)
    private String activite;
    
    @Column
    private double capital=0.0;
    
    @Column
    private double postValue=0.0;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="startup")
	private Set<Fondateur> fondateurs;
        
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="startup")
    private Set<Participation> participations;
    
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="startup")
    private Set<ClubAmi> clubs;
    
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="startup")
    private Set<LeveeDeFonds> leveeDeFonds;
        
    public Startup() {}
	
    public Startup(String nom, String activite, Fondateur f){
		this.nomStartup = nom;
		this.activite = activite;
		fondateurs = new HashSet<Fondateur>();
		fondateurs.add(f);
		f.setStartup(this);
	    participations = new HashSet<Participation>();
	    clubs = new HashSet<ClubAmi>();
	    leveeDeFonds = new HashSet<LeveeDeFonds>();
    }
    
    public void addFondateur(Fondateur f){
        fondateurs.add(f);
    }
    
    public void addParticipation(Participation p){
        participations.add(p);
    }
    
    public void addClub(ClubAmi c){
        clubs.add(c);
    }
    
    public void addLeveeDeFonds(LeveeDeFonds l){
    	leveeDeFonds.add(l);
    }
    
    public Set<LeveeDeFonds> getLevee() {
        return leveeDeFonds;
    }

    public void setLevee(Set<LeveeDeFonds> levee) {
        this.leveeDeFonds = levee;
    }

    public String getActivite() {
        return activite;
    }

    public Set<ClubAmi> getClubs() {
        return clubs;
    }

    public Set<Fondateur> getFondateurs() {
        return fondateurs;
    }

    public long getIdStartup() {
        return idStartup;
    }

    public String getNomStartup() {
        return nomStartup;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public void setClubs(Set<ClubAmi> clubs) {
        this.clubs = clubs;
    }

    public void setFondateurs(Set<Fondateur> fondateurs) {
        this.fondateurs = fondateurs;
    }

    public void setNomStartup(String nomStartup) {
        this.nomStartup = nomStartup;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getPostValue() {
		return postValue;
	}

	public void setPostValue(double postValue) {
		this.postValue = postValue;
	}

	@Override
	public String toString() {
		return "Startup [idStartup=" + idStartup + ", nomStartup=" + nomStartup
				+ ", activite=" + activite + "]";
	}
}
