package gestion_events;

import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Fondateur;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(schema="IFStartupBD", name="Startup")
public class Startup implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int idStartup;
        
	@Basic(optional=false)
	private String nomStartup;
	
        @Column (nullable=false)
        private String activite;
        
        @Column(nullable=true)
	private double postValue;
	
	@OneToMany(mappedBy="Startup")
	private Set<Fondateur> fondateurs;
        
        @OneToMany (fetch=FetchType.EAGER, mappedBy="Startup")
        private HashSet<Participation> participations;
        
        @OneToMany (mappedBy="Startup")
        private Set<ClubAmi> clubs;
        

    public Startup() {
    }
	
	public Startup(String nom, String activite, Fondateur f){
		this.nomStartup = nom;
		this.activite = activite;
		fondateurs = new HashSet<Fondateur>();
		fondateurs.add(f);
                participations = new HashSet<Participation>();
                clubs = new HashSet<ClubAmi>();
	}

    public Set<ClubAmi> getClubs() {
        return clubs;
    }

    public Set<Fondateur> getFondateurs() {
        return fondateurs;
    }

    public HashSet<Participation> getParts() {
        return participations;
    }

    public void setClubs(Set<ClubAmi> clubs) {
        this.clubs = clubs;
    }

    public void setFondateurs(Set<Fondateur> fondateurs) {
        this.fondateurs = fondateurs;
    }

    public void setParts(HashSet<Participation> parts) {
        this.participations = parts;
    }

    public void setPostValue(double postValue) {
        this.postValue = postValue;
    }
	
	
	public void addFondateur(Fondateur f){
		this.fondateurs.add(f);
	}
	
	public int getIdStartup() {
		return idStartup;
	}

	public String getNomStartup() {
		return nomStartup;
	}

	public void setNomStartup(String nomStartup) {
		this.nomStartup = nomStartup;
	}

	public double getPostValue() {
		return postValue;
	}

	public void setPostValue(float postValue) {
		this.postValue = postValue;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
        }
}
