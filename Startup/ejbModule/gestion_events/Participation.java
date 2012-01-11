package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table (name="Participation", uniqueConstraints=@UniqueConstraint(columnNames={"levee_idlevee","startup_idstartup"}))
public class Participation implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private long numParticipation;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (name="levee_idlevee", referencedColumnName="idLevee", nullable=false)
    private LeveeDeFonds leveeDeFonds;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (name="startup_idstartup", referencedColumnName="idStartup", nullable=false)
    private Startup startup;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (referencedColumnName="idInvestisseur", nullable=false)
    private AbstraitInvestisseur investisseur;
    
    @Column (nullable=false)
    private double montant;
    
    public Participation(Startup s, AbstraitInvestisseur i, double d){
        startup = s;
        investisseur = i;
        montant = d;
    }
    
    public Participation(){}

    public LeveeDeFonds getLeveeDeFonds() {
        return leveeDeFonds;
    }

    public double getMontant() {
        return montant;
    }

    public void setInv(AbstraitInvestisseur inv) {
        this.investisseur = inv;
    }

    public AbstraitInvestisseur getInv() {
        return investisseur;
    }

    public long getNumParticipation() {
        return numParticipation;
    }

    public Startup getStart() {
        return startup;
    }

    public void setLeveeDeFonds(LeveeDeFonds levee) {
        this.leveeDeFonds = levee;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setNumParticipation(int numParticipation) {
        this.numParticipation = numParticipation;
    }

    public void setStart(Startup start) {
        this.startup = start;
    }
}
