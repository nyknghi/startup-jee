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
@Table (name="Projet")

public class Projet implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idProjet;
        
	@Basic(optional=false)
	private String nomProjet;

    @Column
    private double capital=0.0;
    
    @Column (nullable=false)
    private String butProjet;
    
    
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="Projet")
	private Set<Entrepreneur> entrepreneurs;
        
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="Projet")
    private Set<Credit> credits;
    
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="Projet")
    private Set<Preteur> preteurs;
    
   
        
    public Projet() {}
	
    public Projet(String nom, String butProjet, Entrepreneur e){
		this.nomProjet = nom;
		entrepreneurs= new HashSet<Entrepreneur>();
		entrepreneurs.add(e);
		e.setProjet(this);
	    credits= new HashSet<Credit>();
	    preteurs = new HashSet<Preteur>();
	   
    }
    
    public void addEntrepreneur(Entrepreneur e){
    	entrepreneurs.add(e);
    }
    
    public void addCredit(Credit c){
       credits.add(c);
    }
    
    public void addPreteur(Preteur p){
        preteurs.add(p);
    }
    
    

    public String getButProjet() {
        return butProjet;
    }

    public Set<Preteur> getPreteur() {
        return preteurs;
    }

    public Set<Entrepreneur> getEntrepreuneur() {
        return entrepreneurs;
    }

    public long getIdProjet() {
        return idProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public Set<Credit> getCredit() {
        return credits;
    }

    
    public void setPreteur(Set<Preteur> preteurs) {
        this.preteurs = preteurs;
    }

    public void setEntrepreuneur(Set<Entrepreneur> entrepreneurs) {
        this.entrepreneurs = entrepreneurs;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public void setCredit(Set<Credit> credits) {
        this.credits = credits;
    }

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}
	public void setbutProjet(String butProjet) {
        this.butProjet = butProjet;
    }

	
	@Override
	public String toString() {
		return "Projet [idProjet=" + idProjet + ", nomProjet=" + nomProjet +"]";
	}
}



