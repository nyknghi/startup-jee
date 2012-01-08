package gestion_investisseurs;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ClubAmi")
public class ClubAmi implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClub;
	@Basic(optional=false)
	private String nomClub;
	@OneToMany(mappedBy="clubAmi")
	List<Membre> businessAngels;

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
}
