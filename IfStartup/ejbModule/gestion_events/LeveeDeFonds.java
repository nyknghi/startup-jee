package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Organisateur;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;

@Entity
@Table (schema="IFStartupBD", name="Levee_de_fonds")
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
    @JoinTable(name="Inscription", joinColumns={@JoinColumn(name="idLevee")}, inverseJoinColumns={@JoinColumn(name="idInvestisseur", referencedColumnName="idInvestisseur")})
    private HashSet<AbstraitInvestisseur> inv;
    
    @OneToMany (mappedBy="Levee")
    private HashSet<Participation> parts;
    
    @ManyToOne
    @Column (name = "Organisateur_fk", nullable = false)
    @JoinColumn (name="Organisateur_fk")
    private Organisateur org;
    
    public LeveeDeFonds (String d, double m, Organisateur o){
        date_levee = d;
        etape = Etape.CANDIDATURE;
        montantCible = m;
        org = o;
        inv = new HashSet<AbstraitInvestisseur>();
        parts = new HashSet<Participation>();
    }

    public String getDate_levee() {
        return date_levee;
    }

    public void setDate_levee(String date_levee) {
        this.date_levee = date_levee;
    }

    public void setInv(HashSet<AbstraitInvestisseur> inv) {
        this.inv = inv;
    }

    public void setOrg(Organisateur org) {
        this.org = org;
    }

    public void setParts(HashSet<Participation> parts) {
        this.parts = parts;
    }

    public HashSet<AbstraitInvestisseur> getInv() {
        return inv;
    }

    public Organisateur getOrg() {
        return org;
    }

    public HashSet<Participation> getParts() {
        return parts;
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
