package gestion_microcredit;

import  java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Utilisateur implements Serializable{
	
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		protected long idUtilisateur;
		@Column(nullable=false)
		protected String nom;
		@Column
		protected String login;
		@Column
		protected String mdp;
		
		@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="investisseur")
		private Set<Credit> credits = new HashSet<Credit>();
		
	
		public long getIdUtilisateur() {
			return idUtilisateur;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getMdp() {
			return mdp;
		}

		public void setMdp(String mdp) {
			this.mdp = mdp;
		}

		public void addCredit(Credit c) {
	        credits.add(c);
	    }
		

}
