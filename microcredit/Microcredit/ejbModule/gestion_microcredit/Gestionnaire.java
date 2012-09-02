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
@Table (name="Gestionnaire")

	
public class Gestionnaire extends Utilisateur{
	
	public Gestionnaire(String nom, String mail, String mdp){
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
	}
	
	public Projet rechercheProjet(){
		return null;
	}
	
	public Credit rechercheCredit(){
		return null;
	}
	
	public Preteur recherchePreteur(){
		return null;
	}

	@Override
	public String toString() {
		return "Preteur [idUtilisateur=" + idUtilisateur + ", nom=" + nom
				+ ", login=" + login + ", mdp=" + mdp +  
				 "]"; 
	}

}
