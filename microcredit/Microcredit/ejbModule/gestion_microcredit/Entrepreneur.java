package gestion_microcredit;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table (name="Entrepreneur")
@NamedQuery(name="findEntrepreneurByName", query="SELECT e FROM Entrepreneur as e WHERE e.nom = :nom")
public class Entrepreneur {
	

	
private static final long serialVersionUID = 465047179273343739L;
	
	@OneToOne
	@JoinColumn(referencedColumnName="idProjet")
	private Projet projet = null;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long idEntrepreneur;
	
	@Column(nullable=false)
	protected String nom;
	public Entrepreneur(){}
	
	public Entrepreneur(String nom){
		this.nom = nom;
		
	}
	
	
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public String getNom() {
		return nom;
	} 
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getIdEntrepreneur() {
		return idEntrepreneur;
	}
	public void setIdEntrepreneur(long idEntrepreneur) {
		this.idEntrepreneur = idEntrepreneur;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrepreneur other = (Entrepreneur) obj;
		if (projet == null) {
			if (other.projet != null)
				return false;
		} else if (!projet.equals(other.projet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrepreneur [id=" + idEntrepreneur + ", nom=" + nom + ", idProjet=" + projet.getIdProjet()+ 
				"]";
	}

}
