package gestion_events;

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
	private int IdStartup;
	@Basic(optional=false)
	private String nomStartup;
	private String activite;
	
        @Basic(optional=true)
	private double capital;
        
        @Column(nullable=true)
	private double postValue;
	
	@OneToMany(mappedBy="startup")
	@JoinColumn(name="fondateur_id")
	private Set<Fondateur> fondateurs;
        
        @OneToMany (mappedBy="Startup")
        private HashSet<Participation> parts;
        

    public Startup() {
    }
	
	public Startup(String nom, String activite, Fondateur f){
		this.nomStartup = nom;
		this.activite = activite;
		fondateurs = new HashSet<Fondateur>();
		fondateurs.add(f);
                parts = new HashSet<Participation>();
	}
	
	
	public void addFondateur(Fondateur f){
		this.fondateurs.add(f);
	}
	
	public int getIdStartup() {
		return IdStartup;
	}

	public String getNomStartup() {
		return nomStartup;
	}

	public void setNomStartup(String nomStartup) {
		this.nomStartup = nomStartup;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(float capital) {
		this.capital = capital;
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
