package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table (name="Inscription", uniqueConstraints=@UniqueConstraint(columnNames={"investisseur_idinvestisseur","levee_idlevee"}))
public class Inscription implements Serializable{
	@Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private long numInscription;
    
    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="levee_idlevee", referencedColumnName="idLevee", nullable=false)
    private LeveeDeFonds leveeDeFonds;
    
    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="investisseur_idinvestisseur", referencedColumnName="idInvestisseur", nullable=false)
    private AbstraitInvestisseur investisseur;
    
    @Column
    private Date date_ins;
    
    public Inscription(){};
    
    public Inscription(LeveeDeFonds levee, AbstraitInvestisseur ainv){
    	this.leveeDeFonds = levee;
    	this.investisseur = ainv;
    	this.date_ins = new Date();
    }

	public long getNumInscription() {
		return numInscription;
	}

	public void setNumInscription(long numInscription) {
		this.numInscription = numInscription;
	}

	public LeveeDeFonds getLeveeDeFonds() {
		return leveeDeFonds;
	}

	public void setLeveeDeFonds(LeveeDeFonds leveeDeFonds) {
		this.leveeDeFonds = leveeDeFonds;
	}

	public AbstraitInvestisseur getInvestisseur() {
		return investisseur;
	}

	public void setInvestisseur(AbstraitInvestisseur investisseur) {
		this.investisseur = investisseur;
	}

	public Date getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(Date date_ins) {
		this.date_ins = date_ins;
	}

	@Override
	public String toString() {
		return "Inscription [numInscription=" + numInscription
				+ ", leveeDeFonds=" + leveeDeFonds.getIdLevee() + ", investisseur="
				+ investisseur.getNom() + ", date_ins=" + date_ins + "]";
	}
}
