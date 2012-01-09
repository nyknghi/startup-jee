package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (schema="IFStartupBD", name="Participation", uniqueConstraints={@UniqueConstraint(columnNames={"levee","startup"})})
public class Participation implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int numParticipation;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column (nullable=true)
    @JoinColumn (referencedColumnName="idLevee")
    private LeveeDeFonds levee;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column (nullable = false)
    @JoinColumn (referencedColumnName="idStartup")
    private Startup startup;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column (nullable = false)
    @JoinColumn (referencedColumnName="idInvestisseur")
    private AbstraitInvestisseur investisseur;
    
    @Column (nullable=false)
    private double montant;
    
    public Participation(Startup s, AbstraitInvestisseur i, double d){
        startup = s;
        investisseur = i;
        montant = d;
    }
    
    public Participation(){}

    public LeveeDeFonds getLevee() {
        return levee;
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

    public int getNumParticipation() {
        return numParticipation;
    }

    public Startup getStart() {
        return startup;
    }

    public void setLevee(LeveeDeFonds levee) {
        this.levee = levee;
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
