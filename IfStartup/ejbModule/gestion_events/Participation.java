package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (schema="IFStartupBD", name="Participation", uniqueConstraints={@UniqueConstraint(columnNames={"Levee_fk","Startup_fk"})})
public class Participation implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int numParticipation;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column (name ="Levee_fk",nullable=true)
    @JoinColumn (name="Levee_fk")
    private LeveeDeFonds levee;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column (name = "Startup_fk", nullable = false)
    @JoinColumn (name="Startup_fk")
    private Startup start;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column (name = "Investisseur_fk", nullable = false)
    @JoinColumn (name="Investisseur_fk")
    private AbstraitInvestisseur inv;
    
    @Column (nullable=false)
    private double montant;
    
    public Participation(Startup s, AbstraitInvestisseur i, double d){
        start = s;
        inv = i;
        montant = d;
    }
    
    public Participation(){}

    public LeveeDeFonds getLevee() {
        return levee;
    }

    public double getMontant() {
        return montant;
    }

    public int getNumParticipation() {
        return numParticipation;
    }

    public Startup getStart() {
        return start;
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
        this.start = start;
    }
}
