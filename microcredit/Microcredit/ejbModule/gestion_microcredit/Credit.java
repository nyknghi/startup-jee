package gestion_microcredit;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity

@Table (name="Credit", uniqueConstraints=@UniqueConstraint(columnNames={"projet_idProjet"}))
	public class Credit implements Serializable {

	    @Id
	    @GeneratedValue (strategy=GenerationType.AUTO)
	    private long idCredit;
	    
	    @ManyToOne (fetch=FetchType.EAGER)
	    @JoinColumn (name="projet_idProjet", referencedColumnName="idProjet", nullable=false)
	    private Projet projet;
	    
	    @ManyToOne (fetch=FetchType.EAGER)
	    @JoinColumn (referencedColumnName="idUtilisateur", nullable=false)
	    private Utilisateur utilisateur;
	    
	    @Column (nullable=false)
	    private double montant;
	    
	    public Credit(Projet p, Utilisateur u, double montant){
	        this.projet = p;
	        this.utilisateur = u;
	        this.montant = montant;
	    }
	    
	    
	    public Credit(){}

	   

	    public double getMontant() {
	        return montant;
	    }

	    public void setUtili(Utilisateur uti) {
	        this.utilisateur = uti;
	    }

	    public Utilisateur getUtili() {
	        return utilisateur;
	    }

	    public long getIdCredit() {
	        return idCredit;
	    }

	    public Projet getStart() {
	        return projet;
	    }

	    public void setMontant(double montant) {
	        this.montant = montant;
	    }

	    public void setIdCredit(int idCredit) {
	        this.idCredit = idCredit;
	    }

	    public void setStart(Projet start) {
	        this.projet = start;
	    }

		@Override
		public String toString() {
			return "Credit [idCredit=" + idCredit
					+ ", projet=" + projet.getNomProjet() + ", preteur=" + utilisateur.getNom()
					+ ", montant=" + montant + "]";
		}

}
