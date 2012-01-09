package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Organisateur;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;

@Entity
@Table (name="Levee_de_fonds")
public class LeveeDeFonds implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)        
    private int idLevee;
    
    @Column (nullable=false)
    private String date_levee;
    
    @Column (nullable=false)
    private Etape etape;
    
    @Column (nullable=false)
    private double montantCible;
    
    @ManyToMany
    @JoinTable(name="Inscription", joinColumns={@JoinColumn(referencedColumnName="idLevee")}, 
            inverseJoinColumns={@JoinColumn(referencedColumnName="idInvestisseur")})
    private HashSet<AbstraitInvestisseur> investisseurs;
    
    @OneToMany (fetch=FetchType.EAGER, mappedBy="levee")
    private HashSet<Participation> participations;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @Column (nullable = false)
    @JoinColumn (referencedColumnName="idInvestisseur")
    private Organisateur organisateur;
    
    public LeveeDeFonds (String d, double m, Organisateur o){
        date_levee = d;
        etape = Etape.CANDIDATURE;
        montantCible = m;
        organisateur = o;
        investisseurs = new HashSet<AbstraitInvestisseur>();
        participations = new HashSet<Participation>();
    }

    public String getDate_levee() {
        return date_levee;
    }

    public void setDate_levee(String date_levee) {
        this.date_levee = date_levee;
    }

    public void setInv(HashSet<AbstraitInvestisseur> inv) {
        this.investisseurs = inv;
    }

    public void setOrg(Organisateur org) {
        this.organisateur = org;
    }

    public void setParts(HashSet<Participation> parts) {
        this.participations = parts;
    }

    public HashSet<AbstraitInvestisseur> getInv() {
        return investisseurs;
    }

    public Organisateur getOrg() {
        return organisateur;
    }

    public HashSet<Participation> getParts() {
        return participations;
    }

    public String getDate() {
        return date_levee;
    }

    public void setDate(String date) {
        this.date_levee = date;
    }

    public void setEtape(Etape e) {
        etape = e;
    }

    public void setIdLevee(int idLevee) {
        this.idLevee = idLevee;
    }

    public void setMontantCible(double montantCible) {
        this.montantCible = montantCible;
    }

    public Etape getEtape() {
        return etape;
    }

    public int getIdLevee() {
        return idLevee;
    }

    public double getMontantCible() {
        return montantCible;
    }
    
    public LeveeDeFonds(){} 
}
