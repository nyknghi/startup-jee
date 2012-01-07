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
	@Basic(optional=false)
	private float capital;
	private float postValue;
	
	@OneToMany(mappedBy="startup")
	@JoinColumn(name="fondateur_id")
	private Set<Fondateur> fondateurs;
	
	public Startup(String nom, String activite, float capital, Fondateur f){
		this.nomStartup = nom;
		this.activite = activite;
		this.capital = capital;
		fondateurs = new HashSet<Fondateur>();
		fondateurs.add(f);
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

	public float getCapital() {
		return capital;
	}

	public void setCapital(float capital) {
		this.capital = capital;
	}

	public float getPostValue() {
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
