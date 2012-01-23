/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inscription;

/**
 *
 * @author UTILISATEUR
 */
public class InscriptionBean {
    String idLevee;
    String date;
    String startup;

    public String getDate() {
        return date;
    }

    public String getIdLevee() {
        return idLevee;
    }

    public String getStartup() {
        return startup;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdLevee(String idLevee) {
        this.idLevee = idLevee;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }

    public InscriptionBean(String idLevee, String date, String startup) {
        this.idLevee = idLevee;
        this.date = date;
        this.startup = startup;
    }
}
