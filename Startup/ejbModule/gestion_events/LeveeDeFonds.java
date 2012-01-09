package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table (name="Leveedefonds")
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
    
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name="Inscription", joinColumns={@JoinColumn(referencedColumnName="idLevee")}, 
            inverseJoinColumns={@JoinColumn(referencedColumnName="idInvestisseur")})
    private Set<AbstraitInvestisseur> investisseurs;
    
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="levee")
    private Set<Participation> participations;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (referencedColumnName="idInvestisseur")
    private AbstraitInvestisseur organisateur;
    
    public LeveeDeFonds (String d, double m, AbstraitInvestisseur o){
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

    public void setInv(Set<AbstraitInvestisseur> inv) {
        this.investisseurs = inv;
    }

    public void setOrg(AbstraitInvestisseur org) {
        this.organisateur = org;
    }

    public void setParts(Set<Participation> parts) {
        this.participations = parts;
    }

    public Set<AbstraitInvestisseur> getInv() {
        return investisseurs;
    }

    public AbstraitInvestisseur getOrg() {
        return organisateur;
    }

    public Set<Participation> getParts() {
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
