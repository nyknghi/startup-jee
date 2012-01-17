/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levees;

/**
 *
 * @author UTILISATEUR
 */
public class ParticipationForm extends org.apache.struts.action.ActionForm {
    
    private String idLevee;
    private String investisseur;
    private String montant;
    private String startup;

    public String getIdLevee() {
        return idLevee;
    }

    public String getInvestisseur() {
        return investisseur;
    }

    public String getMontant() {
        return montant;
    }

    public String getStartup() {
        return startup;
    }

    public void setIdLevee(String idLevee) {
        this.idLevee = idLevee;
    }

    public void setInvestisseur(String investisseur) {
        this.investisseur = investisseur;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }
}
