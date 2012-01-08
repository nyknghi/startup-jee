package gestion_events;

import gestion_investisseurs.Fondateur;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="Startup")
public class Startup implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long IdStartup;
	@Basic(optional=false)
	private String nomStartup;
	private String activite;
	@Basic(optional=false)
	private double capital;
	private double postValue;
	
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="startup")
	@JoinColumn(name="fondateur_id")
	private List<Fondateur> fondateurs;
	
	public Startup(){}
	
	public Startup(String nom, String activite, double capital, Fondateur f){
		this.nomStartup = nom;
		this.activite = activite;
		this.capital = capital;
		fondateurs = new ArrayList<Fondateur>();
		fondateurs.add(f);
		f.setStartup(this);
	}
		
	public void addFondateur(Fondateur f){
		this.fondateurs.add(f);
	}
	
	public long getIdStartup() {
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

	@Override
	public String toString() {
		return "Startup [IdStartup=" + IdStartup + ", nomStartup=" + nomStartup
				+ ", activite=" + activite + "]";
	}
}
