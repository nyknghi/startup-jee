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
	private int idClub;
	@Basic(optional=false)
	private String nomClub;
	@OneToMany(mappedBy="clubAmi", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	List<Membre> businessAngels;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName="idStartup")
	private Startup startup;
	
	public ClubAmi(){}
	
	public ClubAmi(String nom){
		this.nomClub = nom;
		businessAngels = new ArrayList<Membre>();
	}

	public int getIdClub() {
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
}
