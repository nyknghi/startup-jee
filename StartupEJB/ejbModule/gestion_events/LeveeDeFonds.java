package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table (name="Leveedefonds")
public class LeveeDeFonds implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)        
    private long idLevee;
    
    @Column (nullable=false)
    @Temporal(TemporalType.DATE)
    private Date date_levee;
    
    @Column (nullable=false)
    @Enumerated(EnumType.STRING)
    private Etape etape;
    
    @Column (nullable=false)
    private double montantCible;
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (referencedColumnName="idStartup", nullable=false)
    private Startup startup;
    
    @OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="leveeDeFonds")
    private Set<Participation> participations;
    
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="leveeDeFonds")
	private Set<Inscription> inscriptions;
	
    @ManyToOne
    @JoinColumn (referencedColumnName="idInvestisseur", nullable=false)
    private AbstraitInvestisseur organisateur;
    
    public LeveeDeFonds (Date d, double m, AbstraitInvestisseur o, Startup s){
        date_levee = d;
        etape = Etape.CANDIDATURE;
        montantCible = m;
        organisateur = o;
        startup = s;
       // investisseurs = new HashSet<AbstraitInvestisseur>();
        participations = new HashSet<Participation>();
        inscriptions = new HashSet<Inscription>();
    }
    
  /*  public void addInvestisseur(AbstraitInvestisseur i){
        investisseurs.add(i);
    }
    
    public void removeInvestisseur(AbstraitInvestisseur i){
        investisseurs.remove(i);
    }
    */
    public void addParticipation(Participation p){
        participations.add(p);
    }
    
    public LeveeDeFonds(){}

    public Startup getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }

    public Date getDate_levee() {
        return date_levee;
    }

    public Etape getEtape() {
        return etape;
    }

    public long getIdLevee() {
        return idLevee;
    }

   /* public Set<AbstraitInvestisseur> getInvestisseurs() {
        return investisseurs;
    }
*/
    public double getMontantCible() {
        return montantCible;
    }

    public AbstraitInvestisseur getOrganisateur() {
        return organisateur;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setDate_levee(Date date_levee) {
        this.date_levee = date_levee;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

  /*  public void setInvestisseurs(Set<AbstraitInvestisseur> investisseurs) {
        this.investisseurs = investisseurs;
    }
*/
    public void setMontantCible(double montantCible) {
        this.montantCible = montantCible;
    }

    public void setOrganisateur(AbstraitInvestisseur organisateur) {
        this.organisateur = organisateur;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }
    
	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(Set<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@Override
	public String toString() {
		return "LeveeDeFonds [idLevee=" + idLevee + ", date_levee="
				+ date_levee + ", etape=" + etape + ", montantCible="
				+ montantCible + "]";
	}
}
