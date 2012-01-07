package gestion_investisseurs;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="IFStartupBD", name="ClubAmi")
public class ClubAmi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int idClub;
	@Basic(optional=false)
	private String nomClub;

	public ClubAmi(String nom){
		this.nomClub = nom;
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
	
	
}
