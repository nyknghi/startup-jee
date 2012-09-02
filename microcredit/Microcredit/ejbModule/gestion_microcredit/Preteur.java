package gestion_microcredit;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table (name="Preteur")

public class Preteur extends Utilisateur{
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName="idUtilisateur")
	
	
	public Preteur(){}
	
	public Preteur(String nom, String mail, String mdp){
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Preteur [idUtilisateur=" + idUtilisateur + ", nom=" + nom
				+ ", login=" + login + ", mdp=" + mdp +  
				 "]"; 
	}

}
